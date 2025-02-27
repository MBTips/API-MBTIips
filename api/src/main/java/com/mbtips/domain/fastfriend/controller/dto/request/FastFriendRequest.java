package com.mbtips.domain.fastfriend.controller.dto.request;

import com.mbtips.fastfriend.entity.FastFriend;

public record FastFriendRequest(
        String EorI,
        String NorS,
        String TorF,
        String JorP,
        String fastFriendName,
        int fastFriendAge,
        String fastFriendSex,
        String fastFriendRelationship
) {

    public FastFriend toEntity(FastFriendRequest request) {
        return FastFriend.builder()
                .EorI(request.EorI)
                .NorS(request.NorS)
                .TorF(request.TorF)
                .JorP(request.JorP)
                .fastFriendName(request.fastFriendName)
                .fastFriendAge(request.fastFriendAge)
                .fastFriendSex(request.fastFriendSex)
                .fastFriendRelationship(request.fastFriendRelationship)
                .build();
    }
}
