package com.mbtips.common.exception.enums;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.Getter;

@Getter
public enum CommonException implements ExceptionInterface {

    INVALID_REQUEST_ERROR(400, "잘못된 요청입니다."),
    LOGIN_EXPIRATION(403, "로그인이 만료되었습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류입니다."),
    JSON_PROCESS_ERROR(501, "JSON 변형 도중 오류가 발생했습니다."),
    ;

    private final int code;

    private final String message;

    CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
