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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdditionService {

    public final MessageManager messageManager;
    public final ConversationService conversationService;
    public final MessageService messageService;
    public final VirtualFriendService virtualFriendService;

    public List<String> requestConversationTips(Long virtualFriendId) {
        VirtualFriendInfoResponse virtualFriend = virtualFriendService.findFriendInfoById(virtualFriendId);

        String mbtiTrait = MbtiTraits.getTrait(MbtiType.valueOf(virtualFriend.mbti()));
        mbtiTrait = mbtiTrait.substring(mbtiTrait.indexOf("특징을 설명해주면 다음과 같아"));
        String requestPrompt = mbtiTrait + ", 이 mbti와 대화할 때 꿀팁 작성해줘. 대화 주제 별로 번호를 달아 나열해 설명해줘. 4~5개정도만 알려주고, 꿀팁 앞뒤로 쓸데없는 구문은 빼고 답변해줘";
        log.debug("requstPrompt : {}", requestPrompt);
        String response = messageManager.messageRequest(requestPrompt);

        log.debug("result : {}", response);


        List<String> result = extractItems(response);
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

    public List<String> requestRecommendTopic(Long virtualFriendId) {
        VirtualFriendInfoResponse virtualFriend = virtualFriendService.findFriendInfoById(virtualFriendId);
        String mbtiTrait = MbtiTraits.getTrait(MbtiType.valueOf(virtualFriend.mbti()));
        mbtiTrait = mbtiTrait.substring(mbtiTrait.indexOf("특징을 설명해주면 다음과 같아"));
        String requestPrompt = mbtiTrait + ", 이 mbti와 대화할 때, 맞는 대화 주제를 작성해줘. 대화 주제 별로 번호를 달아 나열해 설명해줘. 4~5개정도만 알려주고, 대화 주제 앞뒤로 쓸데없는 구문은 빼고 답변해줘";

        String response = messageManager.messageRequest(requestPrompt);
        log.debug("result : {}", response);

        List<String> result = extractItems(response);
        return result;
    }

    private List<String> extractItems(String text) {
        List<String> items = new ArrayList<>();

        // (?m) : ^, $가 각 라인 시작/끝을 의미하도록
        // (?s) : '.'이 개행문자도 매칭하도록
        // \\d+\\.\\s* : 숫자+마침표+공백
        // (.+?) : 항목 내용 (최소 매칭)
        // (?=\\r?\\n\\d+\\.|\\z) : 다음 항목 번호 또는 문자열 끝 전까지
        String regex = "(?ms)\\d+\\.\\s*(.+?)(?=\\r?\\n\\d+\\.|\\z)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            // group(1)에 번호와 마침표를 제외한 '내용'만 들어있음
            items.add(matcher.group(1).trim());
        }

        return items;
    }
}
