package com.mbtips.message.controller;

import com.mbtips.clova.client.ClovaApiFeignClient;
import com.mbtips.common.annotation.LoginUser;
import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.message.dto.request.CreateMessageRequestDto;
import com.mbtips.domain.virtualfriend.VirtualFriendService;
import com.mbtips.message.application.manager.MessageManager;
import com.mbtips.message.application.service.MessageService;
import com.mbtips.domain.user.User;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    //
    private final MessageService messageService;
    private final MessageManager messageManager;
    private final VirtualFriendService virtualFriendService;
    private final ClovaApiFeignClient clovaApiFeignClient;

    @GetMapping("/{conversationId}")
    public ApiResponse<List<GetMessageResponseDto>> getMessagesOfConversationId(@PathVariable Long conversationId){
        List<GetMessageResponseDto> result = messageService.getMessagesOfConversationId(conversationId);
        return ApiResponse.success(result);
    }

    @PostMapping
    public ApiResponse<String> createMessage(@RequestBody CreateMessageRequestDto createMessageRequestDto, @LoginUser User user){
        //log.debug("<<< controller >>> user value : {}", user.toString());
        String clovaResponse = messageManager.sendMessage(user, createMessageRequestDto);
        return ApiResponse.success(clovaResponse);
    }

}
