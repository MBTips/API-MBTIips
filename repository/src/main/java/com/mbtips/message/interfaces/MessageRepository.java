package com.mbtips.message.interfaces;

import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;

import java.util.List;

public interface MessageRepository {

    List<GetMessageResponseDto> findByConversationId(long conversationId);

    void createMessage(Message message);
}
