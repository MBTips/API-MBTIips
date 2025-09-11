package com.mbtips.openChat.application.service;

import com.mbtips.common.handler.WebSocketChatHandler;
import com.mbtips.domain.openChat.OpenChat;
import com.mbtips.openChat.application.dto.OpenChatDto;
import com.mbtips.openChat.interfaces.OpenChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenChatService {

    private final OpenChatRepository openChatRepository;

    public void save(OpenChatDto openChatDto) {
        OpenChat openChat = OpenChat.builder()
                .imageUrl(openChatDto.imageUrl())
                .title(openChatDto.title())
                .description(openChatDto.description())
                .isDeleted(false)
                .build();
        long openChatId = openChatRepository.save(openChat);
        WebSocketChatHandler.webSocketSessionMap.put(openChatId, new HashSet<>());
    }

    public List<OpenChat> findAll() {
        return openChatRepository.findActiveOpenChats();
    }
}
