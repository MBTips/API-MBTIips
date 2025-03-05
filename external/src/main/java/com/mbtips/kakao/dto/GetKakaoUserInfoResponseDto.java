package com.mbtips.kakao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetKakaoUserInfoResponseDto(
        long id,
        KakaoAccount kakaoAccount
){
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record KakaoAccount(
            Profile profile
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Profile(
            String nickname
    ) {
    }
}
