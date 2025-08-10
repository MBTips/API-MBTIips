package com.mbtips.domain.fastfriend.controller.dto.request;

import com.mbtips.domain.virtualfriend.enums.Gender;
import jakarta.validation.constraints.Pattern;


public record FastFriendRequest(

        String fastFriendName,

        int fastFriendAge,

        Gender gender,

        @Pattern(
                regexp = "^(INTJ|INTP|ENTJ|ENTP|INFJ|INFP|ENFJ|ENFP|ISTJ|ISFJ|ESTJ|ESFJ|ISTP|ISFP|ESTP|ESFP)$",
                message = "유효하지 않은 mbti입니다. 16가지중 하나를 입력해주세요."
        )
        String mbti,

        String fastFriendJob,

        String freeSetting

) {

}
