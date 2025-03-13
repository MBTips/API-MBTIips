package com.mbtips.message.application.manager;

import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.request.CreateMessageRequestDto;
import com.mbtips.domain.user.User;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageManager {

    private final MessageService messageService;
    private final ConversationService conversationService;

    public void sendMessage(User user, CreateMessageRequestDto createMessageRequestDto) {
        Conversation conversation = conversationService.findById(createMessageRequestDto.conversationId());
        Message message = Message.builder()
                .user(user)
                .conversation(conversation)
                .virtualFriend(conversation.getVirtualFriend())
                .messageContent(createMessageRequestDto.messageContent())
                .build();

        messageService.saveMessage(message);
    }
}
