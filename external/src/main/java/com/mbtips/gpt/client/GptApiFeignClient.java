package com.mbtips.gpt.client;

import com.mbtips.gpt.dto.GptChatRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gpt-chat", url = "https://api.openai.com/v1")
public interface GptApiFeignClient {

    @PostMapping(value = "/chat/completions", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getResponse(
            @RequestHeader("Authorization") String authorization,
            @RequestBody GptChatRequest request
            );

    @GetMapping(value = "/models", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getModelList(
            @RequestHeader("Authorization") String authorization
    );
}
