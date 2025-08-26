package com.mbtips.domain.openChat.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.Getter;

@Getter
public enum OpenChatException implements ExceptionInterface {

    DUPLICATED_NICKNAME(30001, "닉네임이 중복됩니다."),
    NOT_FOUND_OPEN_CHAT(30002, "존재하지 않은 오픈채팅방입니다.")
    ;

    private final int code;

    private final String message;

    OpenChatException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
