package com.mbtips.conversation.repository.impl;

import com.mbtips.common.exception.CustomException;
import com.mbtips.conversation.entity.ConversationEntity;
import com.mbtips.conversation.interfaces.ConversationRepository;
import com.mbtips.conversation.repository.jpa.ConversationJpaRepository;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.converstation.exception.ConversationException;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConversationRepositoryImpl implements ConversationRepository {

    private final ConversationJpaRepository conversationJpaRepository;


    @Override
    @Transactional
    public Conversation save(Conversation conversation) {
        ConversationEntity conversationEntity = new ConversationEntity(conversation);
        return conversationJpaRepository.save(conversationEntity)
                .toDomain();
    }

    @Override
    @Transactional
    public void delete(Conversation conversation) {
        ConversationEntity conversationEntity = new ConversationEntity(conversation);
        conversationJpaRepository.delete(conversationEntity);
    }

    @Override
    public Conversation findById(long conversationId) {
        Optional<ConversationEntity> byId = conversationJpaRepository.findById(conversationId);
        ConversationEntity conversationEntity = byId.get();
        System.out.println(conversationEntity.getUser());
        System.out.println(conversationEntity.getVirtualFriendEntity());
        return conversationEntity.toDomain();
//                .orElseThrow(() -> new CustomException(ConversationException.CONVERSATION_NOT_FOUND))
//                .toDomain();
    }

    @Override
    public Conversation findByVirtualFriend(VirtualFriend virtualFriend) {
        VirtualFriendEntity virtualFriendEntity = new VirtualFriendEntity(virtualFriend);
        return conversationJpaRepository.findByVirtualFriend(virtualFriendEntity)
                .orElseThrow(() -> new CustomException(ConversationException.CONVERSATION_NOT_FOUND))
                .toDomain();
    }
}
