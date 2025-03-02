package com.mbtips.domain.fastfriend.controller.dto.request;

public record FastFriendMessageRequest(
        Long fastFriendId,
        String content
) {
}
