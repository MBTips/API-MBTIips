package com.mbtips.domain.fastfriend.controller.dto.request;

import com.mbtips.domain.virtualfriend.enums.Gender;
import com.mbtips.fastfriend.entity.FastFriend;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record FastFriendRequest(

        String fastFriendName,

        int fastFriendAge,

        String fastFriendRelationship,

        Gender gender,


        @Pattern(
                regexp = "^(INTJ|INTP|ENTJ|ENTP|INFJ|INFP|ENFJ|ENFP|ISTJ|ISFJ|ESTJ|ESFJ|ISTP|ISFP|ESTP|ESFP)$",
                message = "유효하지 않은 mbti입니다. 16가지중 하나를 입력해주세요."
        )
        String mbti,


        List<String> interests

) {

    public FastFriend toEntity(FastFriendRequest request) {
        return FastFriend.builder()
                .mbti(request.mbti)
                .fastFriendName(request.fastFriendName)
                .fastFriendAge(request.fastFriendAge)
                .fastFriendSex(request.gender)
                .fastFriendRelationship(request.fastFriendRelationship)
                .build();
    }
}
