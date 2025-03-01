package com.mbtips.domain.fastfriend.controller.dto.response;

import com.mbtips.fastfriend.entity.FastFriend;

public record FastFriendResponse (
        Long fastFriendId,
        String EorI,
        String NorS,
        String TorF,
        String JorP,
        String fastFriendName,
        int fastFriendAge,
        String fastFriendSex,
        String fastFriendRelationship
){

    public static FastFriendResponse from(FastFriend saveFriend) {
        return new FastFriendResponse(
                saveFriend.getFastFriendId(),
                saveFriend.getEorI(),
                saveFriend.getNorS(),
                saveFriend.getTorF(),
                saveFriend.getJorP(),
                saveFriend.getFastFriendName(),
                saveFriend.getFastFriendAge(),
                saveFriend.getFastFriendSex(),
                saveFriend.getFastFriendRelationship()
        );
    }
}
