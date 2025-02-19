package com.mbtips.common.exception.handler;

import com.mbtips.common.exception.CustomException;
import com.mbtips.common.exception.enums.CommonException;
import com.mbtips.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> customExceptionHandler(CustomException e) {
        log.error("### CustomExceptionHandler : {}", e.getMessage(), e);
        return ApiResponse.fail(e.getException());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> exceptionHandler(IllegalArgumentException e) {
        log.error("### IllegalArgumentExceptionHandler : {}", e.getMessage(), e);
        return ApiResponse.fail(CommonException.INVALID_REQUEST_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> exceptionHandler(Exception e) {
        log.error("### ExceptionHandler : {}", e.getMessage(), e);
        return ApiResponse.fail(CommonException.INTERNAL_SERVER_ERROR);
    }

}
