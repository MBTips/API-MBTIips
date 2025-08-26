package com.mbtips.openChat.interfaces;

import com.mbtips.domain.openChat.OpenChatMessage;

import java.util.List;

public interface OpenChatMessageRepository {

    long save(OpenChatMessage openChatMessage);

    List<OpenChatMessage> findNextOpenChatMessages(Long openChatId, Long openChatMessageId);
}
