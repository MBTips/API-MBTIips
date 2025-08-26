package com.mbtips.openChat.application.service;

import com.mbtips.domain.openChat.OpenChat;
import com.mbtips.openChat.application.dto.OpenChatDto;
import com.mbtips.openChat.interfaces.OpenChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        openChatRepository.save(openChat);
    }

    public List<OpenChat> findAll() {
        return openChatRepository.findActiveOpenChats();
    }
}
