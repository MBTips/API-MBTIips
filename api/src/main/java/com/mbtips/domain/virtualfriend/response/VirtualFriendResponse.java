package com.mbtips.domain.virtualfriend.response;

import com.mbtips.virtualfriend.entity.VirtualFriend;

public record VirtualFriendResponse (
        Long virtualFriendId,
        Long conversationId,
        String EorI,
        String NorS,
        String TorF,
        String JorP,
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
                friend.getEorI(),
                friend.getNorS(),
                friend.getTorF(),
                friend.getJorP(),
                friend.getVirtualFriendName(),
                friend.getVirtualFriendAge(),
                friend.getVirtualFriendSex(),
                friend.getVirtualFriendRelationship()
        );
    }
}
