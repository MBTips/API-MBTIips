package com.mbtips.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.common.constant.Constant;
import com.mbtips.common.provider.JwtProvider;
import com.mbtips.domain.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader(Constant.AUTHORIZATION);

        if (StringUtils.hasText(authorization) && authorization.startsWith(Constant.BEARER)) {
            String token = authorization.replace(Constant.BEARER, Constant.BLANK);
            User user = objectMapper.readValue(jwtProvider.parseToken(token).getSubject(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
