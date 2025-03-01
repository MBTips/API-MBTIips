package com.mbtips.user.application.manager;

import com.embitips.user.entity.UserEntityId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.common.exception.CustomException;
import com.mbtips.common.exception.enums.CommonException;
import com.mbtips.common.provider.JwtProvider;
import com.mbtips.domain.user.User;
import com.mbtips.user.application.dto.LoginUserRequestDto;
import com.mbtips.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;

    public String login(LoginUserRequestDto requestDto) {

        UserEntityId userId = UserEntityId.builder()
                .platform(requestDto.platform())
                .platformId(requestDto.platformId())
                .build();

        User user;
        if (!userService.isExist(userId)) {
            user = userService.save(requestDto.platform(), requestDto.platformId());
        }
        user = userService.findById(userId);
        try {
            return jwtProvider.createToken(objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new CustomException(CommonException.JSON_PROCESS_ERROR);
        }
    }
}
