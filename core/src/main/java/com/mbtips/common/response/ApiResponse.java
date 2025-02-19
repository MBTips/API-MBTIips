package com.mbtips.common.response;

import com.mbtips.common.exception.ExceptionInterface;

public record ApiResponse<T>(
    Header header,
    T data
)
{
    public record Header(
            int code,
            String message
    ) {
        public static Header success() {
            return new Header(200, "success");
        }

        public static Header fail(ExceptionInterface exception) {
            return new Header(exception.getCode(), exception.getMessage());
        }
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(Header.success(), null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Header.success(), data);
    }

    public static <T> ApiResponse<T> fail(ExceptionInterface exception) {
        return new ApiResponse<>(Header.fail(exception), null);
    }
}
