//package com.mbtips.domain.fastfriend.service;
//
//import com.mbtips.clova.client.ClovaApiFeignClient;
//import com.mbtips.common.exception.CustomException;
//import com.mbtips.common.mbtiinfo.MbtiTraits;
//import com.mbtips.common.mbtiinfo.MbtiType;
//import com.mbtips.common.provider.ClovaApiKeyProvider;
//import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendMessageRequest;
//import com.mbtips.domain.fastfriend.controller.dto.request.FastFriendRequest;
//import com.mbtips.domain.fastfriend.exception.FastFriendException;
//import com.mbtips.message.application.manager.MessageManager;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class FastFriendService {
//    private final ClovaApiFeignClient clovaApiFeignClient;
//    private final ClovaApiKeyProvider clovaApiKeyProvider;
//    private final MessageManager messageManager;
//
//    public Long createFastFriend(FastFriendRequest fastFriendRequest) {
//        FastFriend friend = fastFriendRequest.toEntity(fastFriendRequest);
//        FastFriend saveFriend =  fastFriendRepository.save(friend);
//        Long result = saveFriend.getFastFriendId();
//        log.debug("빠른 친구 생성이 완료되었습니다. {}", result);
//        return result;
//    }
//
//    public String messageRequest(FastFriendMessageRequest request) {
//        FastFriend fastFriend = fastFriendRepository.findById(request.fastFriendId()).orElseThrow(() ->
//                new CustomException(FastFriendException.NOT_FOUND));
//        String prompt = getfastFriendPrompt(fastFriend);
//        String result = messageManager.messageRequest(prompt + request.content());
//        log.debug(result);
//        return result;
//    }
//
//    private String getfastFriendPrompt(FastFriend fastFriend) {
//        StringBuilder result = new StringBuilder();
//
//        if(fastFriend.getFastFriendName() != null) result.append(" 너의 이름은 " + fastFriend.getFastFriendName() +"이야. 명심해!");
//        MbtiType mbtiType = MbtiType.valueOf(fastFriend.getMbti());
//        result.append(MbtiTraits.getTrait(mbtiType));
//        if(fastFriend.getFastFriendAge() != 0) result.append(" 너의 나이는 " + fastFriend.getFastFriendAge() + "이야. ");
//        if(fastFriend.getFastFriendSex() != null) result.append(" 너의 성별은 " + fastFriend.getFastFriendSex() + "이야. ");
//        if(fastFriend.getFastFriendRelationship() != null) result.append("너와 나의 관계는 " + fastFriend.getFastFriendRelationship() + "이야");
//        result.append("이제 대화를 시작해보자. 20~40자로 짧게해줘. ");
//
//        log.debug("fastfriend prompt : {}", result.toString());
//        return result.toString();
//    }
//}
