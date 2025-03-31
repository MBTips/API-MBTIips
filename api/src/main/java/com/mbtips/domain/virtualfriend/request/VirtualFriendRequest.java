package com.mbtips.domain.virtualfriend.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mbtips.domain.virtualfriend.enums.Gender;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
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

        Gender gender,

        @Pattern(
                regexp = "^(INTJ|INTP|ENTJ|ENTP|INFJ|INFP|ENFJ|ENFP|ISTJ|ISFJ|ESTJ|ESFJ|ISTP|ISFP|ESTP|ESFP)$",
                message = "유효하지 않은 mbti입니다. 16가지중 하나를 입력해주세요."
        )
        String mbti,

        List<String> interests
        ){
        public static VirtualFriendEntity toEntity(VirtualFriendRequest req, UserEntity userEntity) {
                return VirtualFriendEntity.builder()
                        .user(userEntity)
                        .virtualFriendName(req.friendName)
                        .virtualFriendAge(req.age)
                        .virtualFriendRelationship(req.relationship)
                        .virtualFriendSex(req.gender)
                        .mbti(req.mbti)
                        .build();

        }
}
