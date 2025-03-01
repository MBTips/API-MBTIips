package com.mbtips.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "kakao")
public record KakaoProperties(
        String authorizeUrl,
        String appKey,
        String redirectUrl
) {

}
