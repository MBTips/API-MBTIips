package com.mbtips.fake;

import com.mbtips.common.properties.JwtProperties;
import com.mbtips.common.provider.JwtProvider;

public class FakeObjectFactory {

    private FakeObjectFactory() {}

    private static final JwtProperties jwtProperties = new JwtProperties("API-MBTIPS-JWT-TOKEN-PROPERTIES-SECRET-KEY", 3600000L);
    private static final JwtProvider jwtProvider = new JwtProvider(jwtProperties);

    public static JwtProvider getJwtProvider() {
        return jwtProvider;
    }
}
