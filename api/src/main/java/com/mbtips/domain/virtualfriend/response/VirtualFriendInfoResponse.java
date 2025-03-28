package com.mbtips.domain.virtualfriend.response;

import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.domain.virtualfriend.enums.Gender;

import java.util.List;

public record VirtualFriendInfoResponse(
        Long virtualFriendId,
        String mbti,
        String name,
        int age,
        Gender gender,
        String relationship,
        List<String> interest
) {
    public static VirtualFriendInfoResponse from(VirtualFriend virtualFriend, List<String> interest){
        return new VirtualFriendInfoResponse(
                virtualFriend.getVirtualFriendId(),
                virtualFriend.getMbti(),
                virtualFriend.getName(),
                virtualFriend.getAge(),
                virtualFriend.getGender(),
                virtualFriend.getRelationship(),
                interest
        );
    }
}
