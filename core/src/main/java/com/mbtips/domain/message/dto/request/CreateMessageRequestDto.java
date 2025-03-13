package com.mbtips.domain.message.dto.request;


public record CreateMessageRequestDto(
        Long conversationId,
        String messageContent
) {
}
