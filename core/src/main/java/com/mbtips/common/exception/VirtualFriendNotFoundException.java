package com.mbtips.common.exception;

public class VirtualFriendNotFoundException extends CustomException{
    public VirtualFriendNotFoundException(String message) {
        super(new ExceptionInterface() {
            @Override
            public int getCode() {
                return 404;
            }

            @Override
            public String getMessage() {
                return message;
            }
        });
    }
}
