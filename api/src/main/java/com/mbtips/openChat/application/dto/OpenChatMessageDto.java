package com.mbtips.openChat.application.dto;

public record OpenChatMessageDto (
        String nickname,
        String message,
        long openChatId
) {

}
