package com.mbtips.domain.addition.service;

import com.mbtips.clova.dto.Message;
import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.message.application.manager.MessageManager;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionService {

    public final MessageManager messageManager;
    public final ConversationService conversationService;
    public final MessageService messageService;
    public String requestConversationTips(Long virtualFriendId) {
        String requestPrompt = "대화 주제 추천해줘";
        String result = messageManager.messageRequest(requestPrompt);
        return result;
    }

    public String requestTemperature(Long conversationId) {
        Conversation conversation = conversationService.findById(conversationId);
        List<GetMessageResponseDto> messages =  messageService.getMessagesOfConversationId(conversationId);

        String prompt = "현재까지의 대화 온도 측정해서 0~100 사이의 숫자로만 대답해줘";
        String result = messageManager.messageRequest(prompt);
        return result;
    }

    public String requestRecommendTopic(Long virtualFriendId) {

        String prompt = "지금까지 말한 MBTI와 맞는 대화 주제 5개만 추천해줘";
        String result = messageManager.messageRequest(prompt);

        return result;
    }
}
