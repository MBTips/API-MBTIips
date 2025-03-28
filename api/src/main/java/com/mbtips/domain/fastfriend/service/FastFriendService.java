package com.mbtips.domain.fastfriend.service;

import com.mbtips.clova.client.ClovaApiFeignClient;
import com.mbtips.common.provider.ClovaApiKeyProvider;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendMessageRequest;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.fastfriend.FastFriendRepository;
import com.mbtips.fastfriend.entity.FastFriend;
import com.mbtips.message.application.manager.MessageManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class FastFriendService {
    private final FastFriendRepository fastFriendRepository;
    private final ClovaApiFeignClient clovaApiFeignClient;
    private final ClovaApiKeyProvider clovaApiKeyProvider;
    private final MessageManager messageManager;
    /**
     * to-do
     * - 가상친구생성api 호출 로직 작성
     */
    public Long createFastFriend(FastFriendRequest fastFriendRequest) {
        FastFriend friend = fastFriendRequest.toEntity(fastFriendRequest);
        FastFriend saveFriend =  fastFriendRepository.save(friend);
        Long result = saveFriend.getFastFriendId();
        log.debug("빠른 친구 생성이 완료되었습니다. {}", result);
        return result;
    }

    public String messageRequest(FastFriendMessageRequest request) {
        String result = messageManager.messageRequest(request.content());
        log.debug(result);
        return result;
    }
}
