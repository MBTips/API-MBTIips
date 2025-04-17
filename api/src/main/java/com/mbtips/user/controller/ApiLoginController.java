package com.mbtips.user.controller;

import com.mbtips.common.properties.KakaoProperties;
import com.mbtips.common.response.ApiResponse;
import com.mbtips.kakao.client.KakaoApiFeignClient;
import com.mbtips.kakao.client.KakaoAuthFeignClient;
import com.mbtips.kakao.dto.GetKakaoTokenRequestDto;
import com.mbtips.kakao.dto.GetKakaoTokenResponseDto;
import com.mbtips.kakao.dto.GetKakaoUserInfoResponseDto;
import com.mbtips.user.application.dto.LoginUserRequestDto;
import com.mbtips.user.application.manager.UserManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

import static com.mbtips.common.constant.Constant.AUTHORIZATION_CODE;
import static com.mbtips.common.constant.Constant.BEARER;
import static com.mbtips.domain.user.enums.Platform.KAKAO;

@Slf4j
@RestController
@SecurityRequirements
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
@Tag(name = "회원 가입 및 로그인", description = "카카오 로그인 및 회원 가입")
public class ApiLoginController {

    private final KakaoProperties kakaoProperties;
    private final KakaoAuthFeignClient kakaoAuthFeignClient;
    private final KakaoApiFeignClient kakaoApiFeignClient;
    private final UserManager userManager;

    @GetMapping("/authorize-url")
    @Operation(summary = "카카오 로그인 인증 URL", description = "카카오 로그인 인증 URL 반환")
    public ApiResponse<String> getKakaoLoginUrl(@Parameter @RequestParam(required = false) String redirectUrl) {
        if (redirectUrl == null) {
            redirectUrl = kakaoProperties.redirectUrl();
        }
        String kakaoLoginUrl = MessageFormat.format(kakaoProperties.authorizeUrl(), kakaoProperties.appKey(), redirectUrl);
        return ApiResponse.success(kakaoLoginUrl);
    }

    @GetMapping("/login")
    @Operation(summary = "카카오 로그인 콜백 API(토큰 반환)", description = "카카오 로그인 및 회원 가입")
    public ApiResponse<String> kakaoLoginCallback(@RequestParam("code") String code) {

        GetKakaoTokenRequestDto kakaoTokenRequestDto = new GetKakaoTokenRequestDto(AUTHORIZATION_CODE, kakaoProperties.appKey(), kakaoProperties.redirectUrl(), code);
        GetKakaoTokenResponseDto kakaoTokenResponseDto = kakaoAuthFeignClient.getAuthToken(kakaoTokenRequestDto.toMap());

        String authorization = BEARER + kakaoTokenResponseDto.accessToken();
        GetKakaoUserInfoResponseDto kakaoUserInfoResponseDto = kakaoApiFeignClient.getUserInfo(authorization);

        LoginUserRequestDto loginUserRequestDto = new LoginUserRequestDto(KAKAO, kakaoUserInfoResponseDto.id());
        return ApiResponse.success(userManager.login(loginUserRequestDto));
    }

}
