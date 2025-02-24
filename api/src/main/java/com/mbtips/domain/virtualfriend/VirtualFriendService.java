package com.mbtips.domain.virtualfriend;

import com.mbtips.conversation.entity.Conversation;
import com.mbtips.domain.conversation.ConversationService;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.VirtualFriendRepository;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import com.mbtips.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VirtualFriendService {

    private final VirtualFriendRepository virtualFriendRepository;
    private final ConversationService conversationService;
    public ApiResponse<List<VirtualFriend>> getVirtualFriendsByUserId(Long userId) {
        List<VirtualFriend> friends = virtualFriendRepository.findByUserId(userId);

        return ApiResponse.success(friends);
    }

    public ApiResponse<VirtualFriend> createVirtualFriend(VirtualFriendRequest req) {
        User user = new User();
        user.setUserEmail("test@naver.com");
        user.setUserId(1L);
        VirtualFriend friend = VirtualFriendRequest.toEntity(req, user);
        VirtualFriend saveFriend = virtualFriendRepository.save(friend);

        Conversation conversation = conversationService.createConversation(friend, user);

        return ApiResponse.success(saveFriend);
    }
}
