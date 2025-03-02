package com.mbtips.common.provider;

import com.mbtips.common.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String createToken(String value) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(value)
                .issuedAt(new Date(now))
                .expiration(new Date(now + jwtProperties.expiration()))
                .signWith(jwtProperties.secretKey())
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(jwtProperties.secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
