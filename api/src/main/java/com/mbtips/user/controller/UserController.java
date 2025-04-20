package com.mbtips.user.controller;

import com.mbtips.common.annotation.LoginUser;
import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.user.User;
import com.mbtips.user.application.manager.UserManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirements
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "회원 관련 API", description = "회원 관련 API")
public class UserController {

    private final UserManager userManager;

    @DeleteMapping
    @Operation(summary = "회원 탈퇴 API", description = "로그인된 정보로 회원 탈퇴를 합니다.")
    public ApiResponse<Void> deleteUser(@LoginUser User user) {
        userManager.deleteUser(user.getUserId());
        return ApiResponse.success();
    }
}
