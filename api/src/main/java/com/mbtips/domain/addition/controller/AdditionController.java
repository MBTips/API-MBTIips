package com.mbtips.domain.addition.controller;

import com.mbtips.common.annotation.LoginUser;
import com.mbtips.common.response.ApiResponse;
import com.mbtips.domain.addition.service.AdditionService;
import com.mbtips.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirements
@RequiredArgsConstructor
@RequestMapping("/api/addition")
public class AdditionController {

    private final AdditionService additionService;

    @GetMapping("/tips/{virtualFriendId}")
    @Operation(summary = "대화 꿀팁", description = "해당 AI와의 대화 꿀팁을 반환합니다.")
    public ApiResponse<List<String>> requestConversationTips(@PathVariable Long virtualFriendId, @LoginUser User user){
        List<String> result = additionService.requestConversationTips(virtualFriendId);
        return ApiResponse.success(result);
    }


    @GetMapping("/temperature/{conversationId}")
    @Operation(summary = "대화 온도", description = "현재까지 대화의 온도를 측정합니다.")
    public ApiResponse<String> requestTemperature(@PathVariable Long conversationId){
        String result = additionService.requestTemperature(conversationId);
        return ApiResponse.success(result);
    }

    @GetMapping("/recommendtopic/{virtualFriendId}")
    @Operation(summary = "대화 주제 추천", description = "해당 AI와의 대화 주제를 추천합니다.")
    public ApiResponse<List<String>> reqeustRecommendTopic(@PathVariable Long virtualFriendId) {
        List<String> result = additionService.requestRecommendTopic(virtualFriendId);
        return ApiResponse.success(result);
    }
}
