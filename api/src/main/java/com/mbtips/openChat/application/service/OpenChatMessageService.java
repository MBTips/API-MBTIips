package com.mbtips.openChat.application.service;

import com.mbtips.domain.openChat.OpenChatMessage;
import com.mbtips.openChat.application.dto.OpenChatMessageDto;
import com.mbtips.openChat.interfaces.OpenChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenChatMessageService {

    private final OpenChatMessageRepository openChatMessageRepository;

    public long send(OpenChatMessageDto openChatMessageDto) {
        OpenChatMessage openChatMessage = OpenChatMessage.builder()
                .openChatId(openChatMessageDto.openChatId())
                .nickname(openChatMessageDto.nickname())
                .message(openChatMessageDto.message())
                .build();
        return openChatMessageRepository.save(openChatMessage);
    }

    public List<OpenChatMessage> findNextOpenChatMessages(Long openChatId, Long openChatMessageId) {
        return openChatMessageRepository.findNextOpenChatMessages(openChatId, openChatMessageId);
    }
}
