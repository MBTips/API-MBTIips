package com.mbtips.openChat.application.dto;

public record OpenChatMessageDto (
        int type,
        String nickname,
        String message,
        long openChatId
) {

}
