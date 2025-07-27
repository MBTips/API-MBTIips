package com.mbtips.common.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GptApiKeyProvider {

    @Value("${gpt.api-key}")
    private String apiKey;

    public String apiKey(){
        return apiKey;
    }

}
