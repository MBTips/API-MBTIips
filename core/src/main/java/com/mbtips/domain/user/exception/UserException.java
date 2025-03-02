package com.mbtips.domain.user.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.Getter;

@Getter
public enum UserException implements ExceptionInterface {
    USER_NOT_FOUND(10404, "유저를 찾지 못했습니다."),
    ;

    private final int code;

    private final String message;

    UserException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
