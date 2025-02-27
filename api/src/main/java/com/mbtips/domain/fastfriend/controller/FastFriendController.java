package com.mbtips.domain.fastfriend.controller;

import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.domain.fastfriend.controller.dto.response.FastFriendResponse;
import com.mbtips.domain.fastfriend.service.FastFriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fastfriend")
@RequiredArgsConstructor
public class FastFriendController {

    private final FastFriendService fastFriendService;
    @PostMapping
    public ApiResponse<FastFriendResponse> createFastFriendRequest(@Valid @RequestBody FastFriendRequest fastFriendRequest){
        FastFriendResponse result = fastFriendService.createFastFriend(fastFriendRequest);
        return ApiResponse.success(result);
    }
}
