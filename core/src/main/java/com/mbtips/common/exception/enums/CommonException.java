package com.mbtips.common.exception.enums;

import com.mbtips.common.exception.ExceptionInterface;
import lombok.Getter;

@Getter
public enum CommonException implements ExceptionInterface {

    INVALID_REQUEST_ERROR(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류입니다.")
    ;

    private final int code;

    private final String message;

    CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
