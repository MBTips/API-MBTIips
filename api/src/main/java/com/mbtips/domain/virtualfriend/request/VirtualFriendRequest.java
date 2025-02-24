package com.mbtips.domain.virtualfriend.request;

import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.entity.VirtualFriend;

public record VirtualFriendRequest(
        Long userId,
        String userName,
        int age,
        String relationship,
        String sex,
        String eandi,
        String jandp,
        String nands,
        String tandf
        ){
        public static VirtualFriend toEntity(VirtualFriendRequest req, User user) {
                return VirtualFriend.builder()
                        .user(user)
                        .virtualFriendName(req.userName)
                        .virtualFriendAge(req.age)
                        .virtualFriendRelationship(req.relationship)
                        .virtualFriendSex(req.sex)
                        .eandi(req.eandi)
                        .jandp(req.jandp)
                        .nands(req.nands)
                        .tandf(req.tandf)
                        .build();

        }
}
