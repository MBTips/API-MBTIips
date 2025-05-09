package com.mbtips.domain.fastfriend.controller;

import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendMessageRequest;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.domain.fastfriend.service.FastFriendService;
import com.mbtips.domain.message.dto.request.CreateMessageRequestDto;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriendService;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.message.application.manager.MessageManager;
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
    private final VirtualFriendService virtualFriendService;
    private final MessageManager manager;

    @PostMapping
    @Operation(summary = "빠른대화 생성", description = "빠른 대화 생성을 요청한 후 가상친구Id를 반환받습니다.")
    public ApiResponse<Long> createFastFriendRequest(@Valid @RequestBody FastFriendRequest fastFriendRequest){
        VirtualFriendRequest request = VirtualFriendRequest.from(fastFriendRequest);
        User user = new User("admin");
        VirtualFriendResponse result = virtualFriendService.createVirtualFriend(request, user, "fast");
        Long fastFriendId = result.virtualFriendId();
        return ApiResponse.success(fastFriendId);
    }

    @PostMapping("/message")
    @Operation(summary = "빠른대화 메시지 요청", description = "빠른 대화 친구에게 메시지를 보내고 응답을 받습니다.")
    public ApiResponse<String> fastFriendMessageRequest(@RequestBody FastFriendMessageRequest request) {
        User user = new User("admin");

        CreateMessageRequestDto dto = virtualFriendService.createMessageRequestDto(request);
        String result = manager.sendMessage(user, dto);
        return ApiResponse.success(result);
    }
}
