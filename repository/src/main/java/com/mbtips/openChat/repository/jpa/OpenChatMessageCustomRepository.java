package com.mbtips.openChat.repository.jpa;

import com.mbtips.openChat.entity.OpenChatMessageEntity;

import java.util.List;

public interface OpenChatMessageCustomRepository {

    List<OpenChatMessageEntity> findNextOpenChatMessages(Long openChatId, Long openChatMessageId);
}
