package com.mbtips.domain.addition.service;

import com.mbtips.clova.dto.Message;
import com.mbtips.common.exception.CustomException;
import com.mbtips.common.exception.enums.CommonException;
import com.mbtips.common.mbtiinfo.MbtiTraits;
import com.mbtips.common.mbtiinfo.MbtiType;
import com.mbtips.domain.addtion.exception.AdditionException;
import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.domain.virtualfriend.VirtualFriendService;
import com.mbtips.domain.virtualfriend.response.VirtualFriendInfoResponse;
import com.mbtips.message.application.manager.MessageManager;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdditionService {

    public final MessageManager messageManager;
    public final ConversationService conversationService;
    public final MessageService messageService;
    public final VirtualFriendService virtualFriendService;
    public String requestConversationTips(Long virtualFriendId) {
        VirtualFriendInfoResponse virtualFriend = virtualFriendService.findFriendInfoById(virtualFriendId);

        String mbtiTrait = MbtiTraits.getTrait(MbtiType.valueOf(virtualFriend.mbti()));
        mbtiTrait = mbtiTrait.substring(mbtiTrait.indexOf("특징을 설명해주면 다음과 같아"));
        String requestPrompt = mbtiTrait + ", 이 mbti와 대화할 때, 꿀팁 알려줘";
        log.debug("requstPrompt : {}", requestPrompt);
        String result = messageManager.messageRequest(requestPrompt);
        return result;
    }

    public String requestTemperature(Long conversationId) {

        List<GetMessageResponseDto> messages =  messageService.getMessagesOfConversationId(conversationId);
        log.debug("messages.size: {}", messages.size());
        StringBuilder messageBuilder = new StringBuilder();

        if(messages.size() <= 5) throw new CustomException(AdditionException.TOO_FEW_CONVERSATIONS);
        for(int i = messages.size() - 6; i < messages.size(); i++){
            if(messages.get(i).userId() != null) {
                messageBuilder.append("사용자 : ");
                messageBuilder.append(messages.get(i).messageContent());
            }
            if(messages.get(i).virtualFriendId() != null) {
                messageBuilder.append("가상 친구 : ");
                messageBuilder.append(messages.get(i).messageContent());
            }
            if(i != messages.size() -1 ) messageBuilder.append(", ");
        }
        log.debug("대화 내역 : {}", messageBuilder.toString());
        String prompt = messageBuilder + "현재까지의 대화 온도 측정해서 0~100 사이의 숫자로만 대답해줘 냉정하게 판단해주고, 숫자로만 대답해.";
        log.debug("prompt : {}", prompt);
        String temperatureResponse = messageManager.messageRequest(prompt);
        String result = temperatureResponse.replaceAll("[^0-9]", "");
        return result;
    }

    public String requestRecommendTopic(Long virtualFriendId) {
        VirtualFriendInfoResponse virtualFriend = virtualFriendService.findFriendInfoById(virtualFriendId);
        String mbtiTrait = MbtiTraits.getTrait(MbtiType.valueOf(virtualFriend.mbti()));
        mbtiTrait = mbtiTrait.substring(mbtiTrait.indexOf("특징을 설명해주면 다음과 같아"));
        String requestPrompt = mbtiTrait + ", 이 mbti와 대화할 때, 맞는 대화 주제 키워드 5개만 추천해줘, 짧게 단어로만 이야기해줘";

        String result = messageManager.messageRequest(requestPrompt);
        log.debug("result : {}", result);
        return result;
    }
}
