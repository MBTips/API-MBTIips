package com.mbtips.domain.virtualfriend;

import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.virtualfriend.entity.InterestEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import com.mbtips.virtualfriend.interfaces.InterestRepository;
import com.mbtips.virtualfriend.interfaces.VirtualFriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VirtualFriendService {

    private final VirtualFriendRepository virtualFriendRepository;
    private final ConversationService conversationService;
    private final InterestRepository interestRepository;

    public List<VirtualFriendResponse> getVirtualFriendsByUserId(User user) {
        List<Object[]> friends = virtualFriendRepository.findVirtualFriendAndConversation(user.getUserId());

        return friends.stream()
                .map(row -> {
                    VirtualFriendEntity virtualFriendEntity = (VirtualFriendEntity) row[0];
                    return VirtualFriendResponse.from(virtualFriendEntity.toDomain(), (Long) row[1]);
                })
                .collect(Collectors.toList());
    }

    public VirtualFriendResponse createVirtualFriend(VirtualFriendRequest req, User user) {

        VirtualFriend virtualFriend = VirtualFriend.builder()
                .user(user)
                .name(req.friendName())
                .mbti(req.mbti())
                .age(req.age())
                .gender(req.gender())
                .relationship(req.relationship())
                .build();

        VirtualFriend saveVirtualFriend = virtualFriendRepository.save(virtualFriend);

        List<Interest> interests = req.interests()
                .stream()
                .map(topic -> Interest.builder()
                        .virtualFriend(saveVirtualFriend)
                        .topic(topic)
                        .build())
                .toList();
        interestRepository.saveAll(interests);

        Conversation conversation = conversationService.createConversation(saveVirtualFriend, user);
        return VirtualFriendResponse.from(saveVirtualFriend, conversation.getConversationId());
    }

    public void deleteVirtualFriend(Long virtualFriendId, User user) {

        VirtualFriend virtualFriend = virtualFriendRepository.findById(virtualFriendId);

        conversationService.deleteConversation(virtualFriend);
        virtualFriendRepository.delete(virtualFriend);

    }
}
