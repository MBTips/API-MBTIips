package com.mbtips.kakao.dto;


public record GetKakaoTokenResponseDto(
        String tokenType,
        String accessToken,
        String idToken,
        Integer expiresIn,
        String refreshToken,
        Integer refreshTokenExpiresIn,
        String scope
)
{
}
