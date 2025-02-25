package com.mbtips.domain.virtualfriend;

import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.domain.virtualfriend.response.VirtualFriendResponse;
import com.mbtips.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/virtualfriend")
@Slf4j
@RequiredArgsConstructor
public class VirtualFriendController {

    private final VirtualFriendService virtualFriendService;

    /**
     * todo - userId 추출하여 service전달
     */
    @GetMapping
    public ApiResponse<List<VirtualFriendResponse>> getVirtualFriends(@RequestParam Long userId){
        log.debug(" <<< get virtualFriend List >>>");
        List<VirtualFriendResponse> result = virtualFriendService.getVirtualFriendsByUserId(userId);
        return ApiResponse.success(result);
    }

    /**
     *  todo - request validation 적용, 유저추출 적용
     */
    @PostMapping
    public ApiResponse<VirtualFriendResponse> createVirtualFriend(@RequestBody VirtualFriendRequest virtualFriendRequest){
        Long userId = 1L;
        VirtualFriendResponse result = virtualFriendService.createVirtualFriend(virtualFriendRequest, userId);
        return ApiResponse.success(result);
    }

    /**
     * todo - 유저추출 적용
     */
    @DeleteMapping("/{friendId}")
    public ApiResponse<Void> deleteVirtualFriend(@PathVariable Long friendId){
        Long userId = 1L;
        virtualFriendService.deleteVirtualFriend(friendId, userId);
        return ApiResponse.success();
    }
}
