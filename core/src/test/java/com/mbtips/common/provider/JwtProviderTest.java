package com.mbtips.common.provider;

import com.mbtips.common.exception.CustomException;
import com.mbtips.fake.FakeObjectFactory;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

    private final JwtProvider jwtProvider = FakeObjectFactory.getJwtProvider();

    @Test
    @DisplayName("id로 JWT 생성, 파싱 후 동일")
    void givenUserIdWhenCreateTokenAndParseThenSameTrue(){
        // given
        String value = "MBTIPS";

        // when
        String token = jwtProvider.createToken(value);
        Claims claims = jwtProvider.parseToken(token);

        // then
        Assertions.assertEquals(value, claims.getSubject());
    }
}