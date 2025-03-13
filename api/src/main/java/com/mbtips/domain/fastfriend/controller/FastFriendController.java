package com.mbtips.domain.fastfriend.controller;

import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendMessageRequest;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.domain.fastfriend.service.FastFriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirements
@RequiredArgsConstructor
@RequestMapping("/api/fast-friend")
public class FastFriendController {

    private final FastFriendService fastFriendService;

    @PostMapping
    @Operation(summary = "빠른대화 생성", description = "빠른 대화 생성을 요청한 후 가상친구Id를 반환받습니다.")
    public ApiResponse<Long> createFastFriendRequest(@Valid @RequestBody FastFriendRequest fastFriendRequest){
        Long fastFriendId = fastFriendService.createFastFriend(fastFriendRequest);
        return ApiResponse.success(fastFriendId);
    }

    @PostMapping("/message")
    @Operation(summary = "빠른대화 메시지 요청", description = "빠른 대화 친구에게 메시지를 보내고 응답을 받습니다.")
    public ApiResponse<String> fastFriendMessageRequest(@RequestBody FastFriendMessageRequest request) {

        String result = fastFriendService.messageRequest(request);
        return ApiResponse.success(result);
    }
}
