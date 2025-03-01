package com.mbtips.kakao.dto;

import java.util.HashMap;
import java.util.Map;

public record GetKakaoTokenRequestDto(
    String grantType,
    String clientId,
    String redirectUrl,
    String code
) {

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("grant_type", grantType);
        map.put("client_id", clientId);
        map.put("redirect_uri", redirectUrl);
        map.put("code", code);
        return map;
    }
}
