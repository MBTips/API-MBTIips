package com.mbtips.domain.addtion.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.Getter;

@Getter
public enum AdditionException implements ExceptionInterface {

    TOO_FEW_CONVERSATIONS(40001, "대화의 개수가 부족합니다.(5회 이상)")

    ;

    private final int code;
    private final String message;

    AdditionException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
