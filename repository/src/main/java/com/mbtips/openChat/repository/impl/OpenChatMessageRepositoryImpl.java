package com.mbtips.openChat.repository.impl;

import com.mbtips.domain.openChat.OpenChatMessage;
import com.mbtips.openChat.entity.OpenChatMessageEntity;
import com.mbtips.openChat.interfaces.OpenChatMessageRepository;
import com.mbtips.openChat.repository.jpa.OpenChatMessageCustomRepository;
import com.mbtips.openChat.repository.jpa.OpenChatMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpenChatMessageRepositoryImpl implements OpenChatMessageRepository {

    private final OpenChatMessageJpaRepository openChatMessageJpaRepository;
    private final OpenChatMessageCustomRepository openChatMessageCustomRepository;

    @Override
    public long save(OpenChatMessage openChatMessage) {
        return openChatMessageJpaRepository.save(new OpenChatMessageEntity(openChatMessage))
                .getOpenChatMessageId();
    }

    @Override
    public List<OpenChatMessage> findNextOpenChatMessages(Long openChatId, Long openChatMessageId) {
        return openChatMessageCustomRepository.findNextOpenChatMessages(openChatId, openChatMessageId)
                .stream()
                .map(OpenChatMessageEntity::toDomain)
                .toList();
    }
}
