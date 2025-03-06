package com.mbtips.domain.virtualfriend;

import com.mbtips.common.exception.VirtualFriendNotFoundException;
import com.mbtips.conversation.entity.Conversation;
import com.mbtips.domain.conversation.ConversationService;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.user.application.service.UserService;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.InterestRepository;
import com.mbtips.virtualfriend.VirtualFriendRepository;
import com.mbtips.virtualfriend.entity.Interest;
import com.mbtips.virtualfriend.entity.InterestId;
import com.mbtips.virtualfriend.entity.VirtualFriend;
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
    private final UserService userService;
    private final InterestRepository interestRepository;

    public List<VirtualFriendResponse> getVirtualFriendsByUserId(String userId) {
        List<Object[]> friends = virtualFriendRepository.findvirtualFriendAndConversation(userId);

        List<VirtualFriendResponse> result = friends.stream()
                .map(row -> VirtualFriendResponse.from((VirtualFriend) row[0], (Long) row[1]))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * todo user탐색 추가
     */
    public VirtualFriendResponse createVirtualFriend(VirtualFriendRequest req, String userId) {

        User user = userService.findById(userId);
        UserEntity userEntity = new UserEntity(user);

        VirtualFriend friend = VirtualFriendRequest.toEntity(req, userEntity);
        VirtualFriend saveFriend = virtualFriendRepository.save(friend);

        List<Interest> interests = new ArrayList<>();
        for(String topic : req.interests()){
            interests.add(Interest.builder()
                    .virtualFriend(saveFriend)
                    .interestId(new InterestId(saveFriend.getVirtualFriendId(), topic))
                    .build());
        }
        interestRepository.saveAll(interests);

        Conversation conversation = conversationService.createConversation(friend, userEntity);

        VirtualFriendResponse result = VirtualFriendResponse.from(saveFriend, conversation.getConversationId());
        return result;
    }
    /**
     * todo user탐색 추가
     */
    public void deleteVirtualFriend(Long friendId, String userId) {

        User user = userService.findById(userId);
        UserEntity userEntity = new UserEntity(user);

        VirtualFriend virtualFriend = virtualFriendRepository.findByFriendId(friendId, userEntity)
                .orElseThrow(() -> new VirtualFriendNotFoundException("가상 친구를 찾을 수 없습니다."));
        conversationService.deleteConversation(virtualFriend);
        virtualFriendRepository.delete(virtualFriend);

    }
}
