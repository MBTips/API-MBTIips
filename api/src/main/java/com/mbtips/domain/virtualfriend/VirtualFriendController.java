package com.mbtips.domain.virtualfriend;

import com.mbtips.common.annotation.LoginUser;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/virtual-friend")
@RequiredArgsConstructor
public class VirtualFriendController {

    private final VirtualFriendService virtualFriendService;

    /**
     * 
     */
    @GetMapping
    @Operation(summary = "가상친구, 채팅방 리스트 조회", description = "userId기준으로 채팅방+가상친구 리스트를 조회합니다.")
    public ApiResponse<List<VirtualFriendResponse>> getVirtualFriends(@LoginUser User user){
        log.debug(" <<< get virtualFriend List >>>");
        List<VirtualFriendResponse> result = virtualFriendService.getVirtualFriendsByUserId(user.getUserId());
        return ApiResponse.success(result);
    }

    /**
     *  todo - request validation 적용
     */
    @PostMapping
    @Operation(summary = "가상친구 생성", description = "가상친구 생성을 요청하며, 채팅방이 생성됩니다.")
    public ApiResponse<VirtualFriendResponse> createVirtualFriend(@Valid @RequestBody VirtualFriendRequest virtualFriendRequest,
                                                                  @LoginUser User user){
        log.debug("virtualFriendReuqest : {}", virtualFriendRequest);
        VirtualFriendResponse result = virtualFriendService.createVirtualFriend(virtualFriendRequest, user.getUserId());
        return ApiResponse.success(result);
    }

    @DeleteMapping("/{friendId}")
    @Operation(summary = "가상친구 삭제", description = "가상친구가 삭제되며, 채팅방이 삭제됩니다.")
    public ApiResponse<Void> deleteVirtualFriend(@PathVariable Long friendId, @LoginUser User user){
        virtualFriendService.deleteVirtualFriend(friendId, user.getUserId());
        return ApiResponse.success();
    }
}
