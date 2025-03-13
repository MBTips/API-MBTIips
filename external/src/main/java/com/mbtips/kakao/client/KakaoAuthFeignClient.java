package com.mbtips.kakao.client;

import com.mbtips.kakao.configuration.KakaoFeignConfiguration;
import com.mbtips.kakao.dto.GetKakaoTokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "kakao-auth", configuration = KakaoFeignConfiguration.class)
public interface KakaoAuthFeignClient {

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    GetKakaoTokenResponseDto getAuthToken(@RequestParam Map<String, String> requestDto);

}
