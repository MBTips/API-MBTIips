package com.mbtips.message.response;

import java.time.LocalDateTime;

public record MessageResponse(
        Long messageId,
        String messageContent,
        LocalDateTime sentAt,
        String userId,
        Long virtualFriendId
) {
    public MessageResponse(Long messageId, String messageContent, LocalDateTime sentAt, String userId, Long virtualFriendId) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.sentAt = sentAt;
        this.userId = userId;
        this.virtualFriendId = virtualFriendId;
    }
}
