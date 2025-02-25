package com.mbtips.domain.virtualfriend;

import com.mbtips.domain.conversation.ConversationService;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.VirtualFriendRepository;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VirtualFriendService {

    private final VirtualFriendRepository virtualFriendRepository;
    private final ConversationService conversationService;

    @Transactional
    public List<VirtualFriendResponse> getVirtualFriendsByUserId(Long userId) {
        List<VirtualFriend> friends = virtualFriendRepository.findByUserId(userId);
        List<VirtualFriendResponse> result = friends.stream().map(friend -> VirtualFriendResponse.from(friend))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * todo user탐색 추가
     */
    @Transactional
    public VirtualFriendResponse createVirtualFriend(VirtualFriendRequest req, Long userId) {

        User user = new User();
        user.setUserEmail("test@naver.com");
        user.setUserId(userId);

        VirtualFriend friend = VirtualFriendRequest.toEntity(req, user);
        VirtualFriend saveFriend = virtualFriendRepository.save(friend);

        conversationService.createConversation(friend, user);

        VirtualFriendResponse result = VirtualFriendResponse.from(saveFriend);
        return result;
    }
    /**
     * todo user탐색 추가
     */
    @Transactional
    public void deleteVirtualFriend(Long friendId, Long userId) {

        User user = new User();
        user.setUserEmail("test@naver.com");
        user.setUserId(userId);

        VirtualFriend virtualFriend = virtualFriendRepository.findByFriendId(friendId, user);
        conversationService.deleteConversation(virtualFriend);
        virtualFriendRepository.delete(virtualFriend);

    }
}
