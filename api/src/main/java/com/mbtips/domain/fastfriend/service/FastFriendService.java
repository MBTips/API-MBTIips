package com.mbtips.domain.fastfriend.service;

import com.mbtips.clova.client.ClovaApiFeignClient;
import com.mbtips.clova.dto.ChatRequest;
import com.mbtips.clova.dto.Message;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendMessageRequest;
import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
import com.mbtips.domain.fastfriend.controller.dto.response.FastFriendResponse;
import com.mbtips.fastfriend.FastFriendRepository;
import com.mbtips.fastfriend.entity.FastFriend;
import com.mbtips.message.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FastFriendService {
    private final FastFriendRepository fastFriendRepository;
    private final ClovaApiFeignClient clovaApiFeignClient;

    private static final String API_KEY = "";
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
        Message message = new Message("user", request.content());
        ChatRequest chatRequest = new ChatRequest(Arrays.asList(message));
        String responseMessage = clovaApiFeignClient.getResponse(
                "Bearer " + API_KEY,
                UUID.randomUUID().toString(),
                chatRequest
        );
        log.debug(responseMessage);
        return responseMessage;
    }
}
