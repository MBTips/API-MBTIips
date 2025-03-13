package com.mbtips.message.application.service;

import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.message.interfaces.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationService conversationService;

    public List<GetMessageResponseDto> getMessagesOfConversationId(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    public void saveMessage(Message message) {
        messageRepository.createMessage(message);
    }
}
