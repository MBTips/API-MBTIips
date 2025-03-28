package com.mbtips.common.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClovaApiKeyProvider {
    @Value("${clova.api-key}")
    private String apiKey;

    public String apiKey(){
        return apiKey;
    }
}
