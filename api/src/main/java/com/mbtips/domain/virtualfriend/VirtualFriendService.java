package com.mbtips.domain.virtualfriend;

import com.mbtips.common.mbtiinfo.MbtiTraits;
import com.mbtips.common.mbtiinfo.MbtiType;
import com.mbtips.conversation.interfaces.ConversationRepository;
import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendInfoResponse;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.message.interfaces.MessageRepository;
import com.mbtips.virtualfriend.entity.InterestEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import com.mbtips.virtualfriend.interfaces.InterestRepository;
import com.mbtips.virtualfriend.interfaces.VirtualFriendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VirtualFriendService {

    private final VirtualFriendRepository virtualFriendRepository;
    private final ConversationService conversationService;
    private final InterestRepository interestRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    @Transactional(readOnly = true)
    public List<VirtualFriendResponse> getVirtualFriendsByUserId(User user) {
        List<Object[]> friends = virtualFriendRepository.findVirtualFriendAndConversation(user.getUserId());

        return friends.stream()
                .map(row -> {
                    VirtualFriendEntity virtualFriendEntity = (VirtualFriendEntity) row[0];
                    return VirtualFriendResponse.from(virtualFriendEntity.toDomain(), (Long) row[1]);
                })
                .collect(Collectors.toList());
    }

    @Transactional
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


    @Transactional
    public void deleteVirtualFriend(Long virtualFriendId) {

        VirtualFriend virtualFriend = virtualFriendRepository.findById(virtualFriendId);

        interestRepository.deleteInterestByVirtualFriend(virtualFriend);

        conversationService.deleteConversation(virtualFriend);
        virtualFriendRepository.delete(virtualFriend);

    }

    public void deleteVirtualFriendByUserId(String userId){
        List<Long> virtualFriendIds = virtualFriendRepository.findVirtualFriendIds(userId);
        log.debug("가상친구 {} 명을 조회하였습니다.", virtualFriendIds.size());

        for(long idx : virtualFriendIds){
            deleteVirtualFriend(idx);
        }
        log.debug("가상친구의 관심사, 대화방, 메시지를 모두 삭제하였습니다. 삭제된 가상친구 : {}", virtualFriendIds.size());
    }


    @Transactional(readOnly = true)
    public VirtualFriendInfoResponse findFriendInfoById(Long virtualFriendId) {
        VirtualFriend virtualFriend = virtualFriendRepository.findById(virtualFriendId);
        List<String> interest = interestRepository.findTopicsByVirtualFriendId(virtualFriendId);
        log.debug("interest : {}", interest);
        VirtualFriendInfoResponse result = VirtualFriendInfoResponse.from(virtualFriend, interest);
        return result;
    }

    @Transactional
    public VirtualFriendInfoResponse updateVirtualFriend(Long virtualFriendId, VirtualFriendRequest req, User user) {
        VirtualFriend virtualFriend = VirtualFriend.builder()
                .user(user)
                .name(req.friendName())
                .mbti(req.mbti())
                .age(req.age())
                .gender(req.gender())
                .relationship(req.relationship())
                .build();
        VirtualFriend updateFriend = virtualFriendRepository.update(virtualFriendId, virtualFriend);
        interestRepository.deleteInterestByVirtualFriend(updateFriend);
        List<Interest> interests = req.interests()
                .stream()
                .map(topic -> Interest.builder()
                        .virtualFriend(updateFriend)
                        .topic(topic)
                        .build())
                .toList();
        interestRepository.saveAll(interests);

        List<String> topics = interests.stream().map(Interest::getTopic).collect(Collectors.toList());
        return VirtualFriendInfoResponse.from(updateFriend, topics);
    }
}
