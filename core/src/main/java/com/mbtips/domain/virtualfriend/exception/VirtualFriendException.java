package com.mbtips.domain.virtualfriend.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VirtualFriendException implements ExceptionInterface {
    NOT_FOUND(404, "가상 친구를 찾을 수 없습니다.");

    private final int code;

    private final String message;
}
