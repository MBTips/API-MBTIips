package com.mbtips.user.application.dto;

import com.mbtips.domain.user.enums.Platform;

public record LoginUserRequestDto(
        Platform platform,
        long platformId
) {
}
