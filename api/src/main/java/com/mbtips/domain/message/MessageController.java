package com.mbtips.domain.message;

import com.mbtips.common.response.ApiResponse;
import com.mbtips.message.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{conversationId}")
    public ApiResponse<List<MessageResponse>> getMessagesOfConversationId(@PathVariable Long conversationId){
        List<MessageResponse> result = messageService.getMessagesOfConversationId(conversationId);
        return ApiResponse.success(result);
    }

}
