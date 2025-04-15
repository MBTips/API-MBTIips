package com.mbtips.message.repository.impl;

import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.message.entity.MessageEntity;
import com.mbtips.message.interfaces.MessageRepository;
import com.mbtips.message.repository.jpa.MessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;

    @Override
    public List<GetMessageResponseDto> findByConversationId(long conversationId) {
        return messageJpaRepository.findByConversationId(conversationId);
    }

    @Override
    @Transactional
    public void createMessage(Message message) {
        MessageEntity messageEntity = new MessageEntity(message);
        messageJpaRepository.save(messageEntity);
    }

    @Override
    public List<GetMessageResponseDto> findRecentMessagesByConversationId(Long conversationId) {
        return messageJpaRepository.findRecentMessagesByConversationId(conversationId, PageRequest.of(0,5));
    }


}
