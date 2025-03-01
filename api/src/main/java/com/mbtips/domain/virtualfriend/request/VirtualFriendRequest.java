package com.mbtips.domain.virtualfriend.request;

import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VirtualFriendRequest(
        Long userId,

        String userName,

        @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
        @Max(value = 100, message = "나이는 100 이하이어야 합니다.")
        int age,

        @NotBlank(message = "관계 유형은 필수입니다.")
        String relationship,

        @Pattern(regexp = "M|F", message = "성별은 M 또는 F만 가능합니다.")
        String sex,

        @Pattern(regexp = "E|I", message = "E 또는 I중 하나이어야 합니다.")
        String EorI,

        @Pattern(regexp = "N|S", message = "N 또는 S중 하나이어야 합니다.")
        String NorS,

        @Pattern(regexp = "T|F", message = "T 또는 F중 하나이어야 합니다.")
        String TorF,

        @Pattern(regexp = "J|P", message = "J 또는 P중 하나이어야 합니다.")
        String JorP

        ){
        public static VirtualFriend toEntity(VirtualFriendRequest req, UserEntity userEntity) {
                return VirtualFriend.builder()
                        .user(userEntity)
                        .virtualFriendName(req.userName)
                        .virtualFriendAge(req.age)
                        .virtualFriendRelationship(req.relationship)
                        .virtualFriendSex(req.sex)
                        .EorI(req.EorI)
                        .JorP(req.JorP)
                        .NorS(req.NorS)
                        .TorF(req.TorF)
                        .build();

        }
}
