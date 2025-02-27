package com.mbtips.domain.fastfriend.service;

import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.domain.fastfriend.controller.dto.response.FastFriendResponse;
import com.mbtips.fastfriend.FastFriendRepository;
import com.mbtips.fastfriend.entity.FastFriend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FastFriendService {
    private final FastFriendRepository fastFriendRepository;

    /**
     * to-do
     * 1. 반환값 변경하기
     * 2. 가상친구생성api 호출 로직 작성
     */
    public FastFriendResponse createFastFriend(FastFriendRequest fastFriendRequest) {
        FastFriend friend = fastFriendRequest.toEntity(fastFriendRequest);
        FastFriend saveFriend =  fastFriendRepository.save(friend);
        FastFriendResponse result = FastFriendResponse.from(saveFriend);
        log.debug("빠른 친구 생성이 완료되었습니다. {}", result.fastFriendId());
        return result;
    }
}
