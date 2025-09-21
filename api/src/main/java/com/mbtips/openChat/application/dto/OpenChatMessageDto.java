package com.mbtips.openChat.application.dto;

import com.mbtips.common.enums.WebSocketMessageType;
import com.mbtips.common.mbtiinfo.MbtiType;
import lombok.Builder;

@Builder
public record OpenChatMessageDto (
        WebSocketMessageType type,
        MbtiType mbti,
        String nickname,
        String message,
        long openChatId
) {

}
