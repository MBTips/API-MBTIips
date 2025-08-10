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
        String job,
        String freeSetting
) {
    public static VirtualFriendInfoResponse from(VirtualFriend virtualFriend){
        return new VirtualFriendInfoResponse(
                virtualFriend.getVirtualFriendId(),
                virtualFriend.getMbti(),
                virtualFriend.getName(),
                virtualFriend.getAge(),
                virtualFriend.getGender(),
                virtualFriend.getJob(),
                virtualFriend.getFreeSetting()
        );
    }
}
