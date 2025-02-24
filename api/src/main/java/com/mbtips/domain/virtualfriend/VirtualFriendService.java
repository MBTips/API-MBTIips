package com.mbtips.domain.virtualfriend;

import com.mbtips.virtualfriend.VirtualFriendRepository;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import com.mbtips.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VirtualFriendService {

    private final VirtualFriendRepository virtualFriendRepository;
    public ApiResponse<List<VirtualFriend>> getVirtualFriendsByUserId(Long userId) {
        List<VirtualFriend> friends = virtualFriendRepository.findByUserId(userId);

        return ApiResponse.success(friends);
    }
}
