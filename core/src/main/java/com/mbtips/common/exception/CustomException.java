package com.mbtips.common.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionInterface exception;

    public CustomException(ExceptionInterface exception) {
        super(exception.getMessage());
        this.exception = exception;
    }
}

