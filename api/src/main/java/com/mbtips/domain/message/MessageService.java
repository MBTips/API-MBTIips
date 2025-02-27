package com.mbtips.domain.message;

import com.mbtips.message.response.MessageResponse;
import com.mbtips.message.MessageRepository;
import com.mbtips.message.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;



    public List<MessageResponse> getMessagesOfConversationId(Long conversationId) {
        List<MessageResponse> messages = messageRepository.findByConversationId(conversationId);
        return messages;
    }
}
