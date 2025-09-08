package com.mbtips.domain.virtualfriend.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.domain.virtualfriend.enums.Gender;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VirtualFriendRequest(

        @Column(nullable = false)
        String friendName,

        int age,

        Gender gender,

        @Pattern(
                regexp = "^(INTJ|INTP|ENTJ|ENTP|INFJ|INFP|ENFJ|ENFP|ISTJ|ISFJ|ESTJ|ESFJ|ISTP|ISFP|ESTP|ESFP)$",
                message = "유효하지 않은 mbti입니다. 16가지중 하나를 입력해주세요."
        )
        String mbti,

        String job,

        String freeSetting

        ){


        public static VirtualFriendEntity toEntity(VirtualFriendRequest req, UserEntity userEntity) {
                return VirtualFriendEntity.builder()
                        .user(userEntity)
                        .virtualFriendName(req.friendName)
                        .virtualFriendAge(req.age)
                        .virtualFriendSex(req.gender)
                        .mbti(req.mbti)
                        .build();

        }

        public static VirtualFriendRequest from(FastFriendRequest fastFriendRequest) {
                return new VirtualFriendRequest(
                        Optional.ofNullable(fastFriendRequest.fastFriendName()).orElse("빠른친구"),
                        Optional.ofNullable(fastFriendRequest.fastFriendAge()).orElse(20),
                        Optional.ofNullable(fastFriendRequest.gender()).orElse(null),
                        Optional.ofNullable(fastFriendRequest.mbti()).orElse(null),
                        Optional.ofNullable(fastFriendRequest.fastFriendJob()).orElse(null),
                        Optional.ofNullable(fastFriendRequest.freeSetting()).orElse(null)
                        );
        }
}
