package com.mbtips.kakao.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetKakaoTokenResponseDto(
        @JsonProperty("access_token")
        String accessToken
)
{
}