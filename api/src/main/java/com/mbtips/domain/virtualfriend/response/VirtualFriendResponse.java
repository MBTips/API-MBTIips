package com.mbtips.domain.virtualfriend.response;

import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.domain.virtualfriend.enums.Gender;

public record VirtualFriendResponse (
        Long virtualFriendId,
        Long conversationId,
        String mbti,
        String virtualFriendName,
        int virtualFriendAge,
        Gender virtualFriendSex,
        String virtualFriendRelationship
){
    public static VirtualFriendResponse from(VirtualFriend virtualFriend , long conversationId) {
        return new VirtualFriendResponse(
                virtualFriend.getVirtualFriendId(),
                conversationId,
                virtualFriend.getMbti(),
                virtualFriend.getName(),
                virtualFriend.getAge(),
                virtualFriend.getGender(),
                virtualFriend.getRelationship()
        );
    }

}
