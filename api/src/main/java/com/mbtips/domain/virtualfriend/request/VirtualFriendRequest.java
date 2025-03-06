package com.mbtips.domain.virtualfriend.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VirtualFriendRequest(

        String friendName,

        @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
        @Max(value = 100, message = "나이는 100 이하이어야 합니다.")
        int age,

        @NotBlank(message = "관계 유형은 필수입니다.")
        String relationship,

        @Pattern(regexp = "M|F", message = "성별은 M 또는 F만 가능합니다.")
        String sex,

        @Pattern(
                regexp = "^(INTJ|INTP|ENTJ|ENTP|INFJ|INFP|ENFJ|ENFP|ISTJ|ISFJ|ESTJ|ESFJ|ISTP|ISFP|ESTP|ESFP)$",
                message = "유효하지 않은 mbti입니다. 16가지중 하나를 입력해주세요."
        )
        String mbti,

        List<String> interests
        ){
        public static VirtualFriend toEntity(VirtualFriendRequest req, UserEntity userEntity) {
                return VirtualFriend.builder()
                        .user(userEntity)
                        .virtualFriendName(req.friendName)
                        .virtualFriendAge(req.age)
                        .virtualFriendRelationship(req.relationship)
                        .virtualFriendSex(req.sex)
                        .mbti(req.mbti)
                        .build();

        }
}
