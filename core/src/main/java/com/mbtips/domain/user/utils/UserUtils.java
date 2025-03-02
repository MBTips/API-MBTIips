package com.mbtips.domain.user.utils;

import com.mbtips.domain.user.enums.Platform;

public class UserUtils {

    private UserUtils() {}

    public static String getUserId(Platform platform, long platformId) {
        return platform.name() + ":" + platformId;
    }
}
