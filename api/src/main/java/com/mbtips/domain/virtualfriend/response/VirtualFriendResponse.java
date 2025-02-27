package com.mbtips.domain.virtualfriend.response;

import com.mbtips.virtualfriend.entity.VirtualFriend;

public record VirtualFriendResponse (
        Long virtualFriendId,
        String EorI,
        String NorS,
        String TorF,
        String JorP,
        String virtualFriendName,
        int virtualFriendAge,
        String virtualFriendSex,
        String virtualFriendRelationship
){
    public static VirtualFriendResponse from(VirtualFriend friend) {
        return new VirtualFriendResponse(
                friend.getVirtualFriendId(),
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
