package com.mbtips.domain.fastfriend.exception;

import com.mbtips.common.exception.interfaces.ExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FastFriendException implements ExceptionInterface {
    NOT_FOUND(404, "빠른 친구를 찾을 수 없습니다.");

    private final int code;

    private final String message;
}
