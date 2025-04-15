package com.mbtips.common.exception.handler;

import com.mbtips.common.exception.CustomException;
import com.mbtips.common.exception.ErrorResponse;
import com.mbtips.common.exception.enums.CommonException;
import com.mbtips.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    // 반환타입 void -> ErrorResponse, 클라이언트에서 코드, 메시지를 확인하도록
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
        log.error("### CustomExceptionHandler : {}", e.getMessage(), e);
//        return ResponseEntity.internalServerError().build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(e.getException().getCode(), e.getException().getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> exceptionHandler(IllegalArgumentException e) {
        log.error("### IllegalArgumentExceptionHandler : {}", e.getMessage(), e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> exceptionHandler(Exception e) {
        log.error("### ExceptionHandler : {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> exceptionsHandler(MethodArgumentNotValidException e) {

        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        log.error("### Validation Errors: {}", errorMessages);

        return ApiResponse.fail(CommonException.INVALID_REQUEST_ERROR);
    }



}
