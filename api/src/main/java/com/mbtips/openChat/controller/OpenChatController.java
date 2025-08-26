package com.mbtips.openChat.controller;

import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.openChat.OpenChat;
import com.mbtips.domain.openChat.OpenChatMessage;
import com.mbtips.openChat.application.dto.OpenChatDto;
import com.mbtips.openChat.application.service.OpenChatMessageService;
import com.mbtips.openChat.application.service.OpenChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/open-chat")
public class OpenChatController {

    private final OpenChatService openChatService;
    private final OpenChatMessageService openChatMessageService;

    @PostMapping
    public ApiResponse<Void> createOpenChat(@RequestBody OpenChatDto openChatDto) {
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<OpenChat>> getOpenChats() {
        return ApiResponse.success(openChatService.findAll());
    }

    @GetMapping("/{openChatId}")
    public ApiResponse<List<OpenChatMessage>> getOpenChatMessages(@PathVariable("openChatId") long openChatId,
                                                                  @RequestParam(required = false, defaultValue = "0") long openChatMessageId) {
        return ApiResponse.success(openChatMessageService.findNextOpenChatMessages(openChatId, openChatMessageId));
    }
}
