package com.mbtips.common.properties;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String secret,
        long expiration
) {

    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(this.secret.getBytes());
    }
}
