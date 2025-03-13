package com.mbtips.domain.converstation.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.Getter;

@Getter
public enum ConversationException implements ExceptionInterface {
    CONVERSATION_NOT_FOUND(20404, "대화방을 찾지 못했습니다."),
    ;

    private final int code;

    private final String message;

    ConversationException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
