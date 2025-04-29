package com.mbtips.message.repository.impl;

import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.message.entity.MessageEntity;
import com.mbtips.message.interfaces.MessageRepository;
import com.mbtips.message.repository.jpa.MessageJpaRepository;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.user.interfaces.UserRepository;
import com.mbtips.user.repository.jpa.UserJpaRepository;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import com.mbtips.virtualfriend.interfaces.VirtualFriendRepository;
import com.mbtips.virtualfriend.repository.jpa.VirtualFriendJpaRepository;
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
    private final UserJpaRepository userJpaRepository;
    private final VirtualFriendJpaRepository virtualFriendJpaRepository;

    @Override
    public List<GetMessageResponseDto> findByConversationId(long conversationId) {
        return messageJpaRepository.findByConversationId(conversationId);
    }

    @Override
    @Transactional
    public void createMessage(Message message) {
        UserEntity userEntity = null;
        VirtualFriendEntity virtualFriend = null;
        if(message.getUser() != null) userEntity = userJpaRepository.findById(message.getUser().getUserId()).get();
        if(message.getVirtualFriend() != null) virtualFriend = virtualFriendJpaRepository.findById(message.getVirtualFriend().getVirtualFriendId()).get();
        MessageEntity messageEntity = new MessageEntity(message, userEntity, virtualFriend);
        messageJpaRepository.save(messageEntity);
    }

    @Override
    public List<GetMessageResponseDto> findRecentMessagesByConversationId(Long conversationId) {
        return messageJpaRepository.findRecentMessagesByConversationId(conversationId, PageRequest.of(0,5));
    }



}
