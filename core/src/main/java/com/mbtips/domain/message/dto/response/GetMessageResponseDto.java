package com.mbtips.domain.message.dto.response;

import java.time.LocalDateTime;

public record GetMessageResponseDto(
        Long messageId,
        String messageContent,
        LocalDateTime sentAt,
        String userId,
        Long virtualFriendId
) {
}
