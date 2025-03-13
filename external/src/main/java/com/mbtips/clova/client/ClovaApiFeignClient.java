package com.mbtips.clova.client;

import com.mbtips.clova.dto.ChatRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "clova-chat", url = "https://clovastudio.stream.ntruss.com/testapp/v2/tasks/czfsttyp")
public interface ClovaApiFeignClient {
    @PostMapping(value = "/chat-completions", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getResponse(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-NCP-CLOVASTUDIO-REQUEST-ID") String requestId,
            @RequestBody ChatRequest request
    );
}
