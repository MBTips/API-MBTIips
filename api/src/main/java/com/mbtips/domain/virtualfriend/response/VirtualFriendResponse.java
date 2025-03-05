package com.mbtips.domain.virtualfriend.response;

import com.mbtips.virtualfriend.entity.VirtualFriend;

public record VirtualFriendResponse (
        Long virtualFriendId,
        Long conversationId,
        String mbti,
        String virtualFriendName,
        int virtualFriendAge,
        String virtualFriendSex,
        String virtualFriendRelationship
){
    public VirtualFriendResponse VirtualFriendResponse(VirtualFriend friend, Long conversationId) {
        return from(friend, conversationId);
    }
    public static VirtualFriendResponse from(VirtualFriend friend ,Long conversationId) {
        return new VirtualFriendResponse(
                friend.getVirtualFriendId(),
                conversationId,
                friend.getMbti(),
                friend.getVirtualFriendName(),
                friend.getVirtualFriendAge(),
                friend.getVirtualFriendSex(),
                friend.getVirtualFriendRelationship()
        );
    }

}
