package com.mbtips.domain.user;

import com.mbtips.domain.user.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Platform platform;

    private long platformId;

}
