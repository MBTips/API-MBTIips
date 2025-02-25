package com.mbtips.domain.virtualfriend;

import com.mbtips.domain.virtualfriend.request.VirtualFriendRequest;
import com.mbtips.virtualfriend.dto.VirtualFriendDto;
import com.mbtips.virtualfriend.entity.VirtualFriend;
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

    @GetMapping
    public ApiResponse<List<VirtualFriendDto>> getVirtualFriends(@RequestParam Long userId){
        System.out.println("test");
        log.debug("get VirtualFriends");
        return virtualFriendService.getVirtualFriendsByUserId(userId);
    }

    @PostMapping
    public ApiResponse<VirtualFriend> createVirtualFriend(@RequestBody VirtualFriendRequest virtualFriendRequest){
        return virtualFriendService.createVirtualFriend(virtualFriendRequest);
    }

    @DeleteMapping("/{friendId}")
    public ApiResponse<Void> deleteVirtualFriend(@PathVariable Long friendId){
        virtualFriendService.deleteVirtualFriend(friendId);
        return ApiResponse.success();
    }
}
