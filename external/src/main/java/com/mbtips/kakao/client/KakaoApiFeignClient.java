package com.mbtips.kakao.client;

import com.mbtips.common.constant.Constant;
import com.mbtips.kakao.configuration.KakaoFeignConfiguration;
import com.mbtips.kakao.dto.GetKakaoUserInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao-api", configuration = KakaoFeignConfiguration.class)
public interface KakaoApiFeignClient {

    @GetMapping(value = "/v2/user/me",consumes = "application/x-www-form-urlencoded;charset=utf-8")
    GetKakaoUserInfoResponseDto getUserInfo(@RequestHeader(Constant.AUTHORIZATION) String token);
}
