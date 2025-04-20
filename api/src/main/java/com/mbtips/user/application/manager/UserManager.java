package com.mbtips.user.application.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.common.exception.CustomException;
import com.mbtips.common.exception.enums.CommonException;
import com.mbtips.common.provider.JwtProvider;
import com.mbtips.domain.user.User;
import com.mbtips.domain.user.utils.UserUtils;
import com.mbtips.user.application.dto.LoginUserRequestDto;
import com.mbtips.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;

    @Transactional
    public String login(LoginUserRequestDto requestDto) {
        String userId = UserUtils.getUserId(requestDto.platform(), requestDto.platformId());
        User user = userService.joinAndLogin(userId);
        try {
            return jwtProvider.createToken(objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new CustomException(CommonException.JSON_PROCESS_ERROR);
        }
    }

    @Transactional
    public void deleteUser(String userId) {
        userService.delete(userId);
        // TODO : 회원 탈퇴시 진행되어야 하는 구조를 여기 넣으면 됩니다.
    }
}
