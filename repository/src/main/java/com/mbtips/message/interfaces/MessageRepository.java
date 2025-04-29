package com.mbtips.message.interfaces;

import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.domain.virtualfriend.VirtualFriend;

import java.util.List;

public interface MessageRepository {

    List<GetMessageResponseDto> findByConversationId(long conversationId);

    void createMessage(Message message);

    List<GetMessageResponseDto> findRecentMessagesByConversationId(Long conversationId);

}
