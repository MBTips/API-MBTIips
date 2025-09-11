package com.mbtips.openChat.repository.impl;

import com.mbtips.domain.openChat.OpenChat;
import com.mbtips.openChat.entity.OpenChatEntity;
import com.mbtips.openChat.interfaces.OpenChatRepository;
import com.mbtips.openChat.repository.jpa.OpenChatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpenChatRepositoryImpl implements OpenChatRepository {

    private final OpenChatJpaRepository openChatJpaRepository;

    @Override
    public long save(OpenChat openChat) {
        return openChatJpaRepository.save(new OpenChatEntity(openChat)).getOpenChatId();
    }

    @Override
    public List<OpenChat> findActiveOpenChats() {
        return openChatJpaRepository.findAllByIsDeletedFalse()
                .stream()
                .map(OpenChatEntity::toDomain)
                .toList();
    }
}
