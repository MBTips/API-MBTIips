package com.mbtips.message.application.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.clova.client.ClovaApiFeignClient;
import com.mbtips.clova.dto.ChatRequest;
import com.mbtips.clova.dto.ClovaApiResponse;
import com.mbtips.common.mbtiinfo.MbtiTraits;
import com.mbtips.common.mbtiinfo.MbtiType;
import com.mbtips.common.provider.ClovaApiKeyProvider;
import com.mbtips.conversation.interfaces.ConversationRepository;
import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.request.CreateMessageRequestDto;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageManager {

    private final Integer MAX_TOKEN_VALUE = 300;
    private final MessageService messageService;
    private final ConversationService conversationService;
    private final ClovaApiFeignClient clovaApiFeignClient;
    private final ClovaApiKeyProvider clovaApiKeyProvider;
    private final ConversationRepository conversationRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public String sendMessage(User user, CreateMessageRequestDto createMessageRequestDto) {
        Conversation conversation = conversationService.findById(createMessageRequestDto.conversationId());
        List<GetMessageResponseDto> messages = messageService.getRecentMessagesOfConversationId(conversation.getConversationId());
        Collections.reverse(messages);
        //log.debug("<<<service start >>> userInfo : {}", user);
        Message requestMessage = Message.builder()
                .user(user)
                .conversation(conversation)
//                .virtualFriend(conversation.getVirtualFriend())
                .messageContent(createMessageRequestDto.messageContent())
                .build();
        log.debug("message : {}", requestMessage.toString());
        messageService.saveMessage(requestMessage);


        String prompt = makePrompt(createMessageRequestDto.conversationId());

        String recentMessagesPrompt = makeRecentMessageString(messages);
        prompt += recentMessagesPrompt;
        prompt = prompt + " 이제 대화를 시작해보자!";
        log.debug("prompt : {} ", prompt);

        String responseContent = messageRequest(prompt + " " + createMessageRequestDto.messageContent());
        log.debug("responseMessage : {} ", responseContent);


        Message responseMessage = Message.builder()
                .conversation(conversation)
                .virtualFriend(conversation.getVirtualFriend())
                .messageContent(responseContent)
                .build();

        messageService.saveMessage(responseMessage);
        return responseContent;
    }

    private String makeRecentMessageString(List<GetMessageResponseDto> messages) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(" 지금까지의 대화내용은 다음과 같아. B가 지금까지 너의 대답이야. ");
        int idx = messages.size() - 6;
        if(idx < 0) idx = 0;
        for(int i = idx; i < messages.size(); i++){
            if(messages.get(i).userId() != null) {
                messageBuilder.append("A : ");
                messageBuilder.append(messages.get(i).messageContent());
            }
            if(messages.get(i).virtualFriendId() != null) {
                messageBuilder.append("B : ");
                messageBuilder.append(messages.get(i).messageContent());
            }
            if(i != messages.size() -1 ) messageBuilder.append(", ");
        }

        return messageBuilder.toString();
    }

    private String makePrompt(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId);
        VirtualFriend virtualFriend = conversation.getVirtualFriend();


        String mbti = virtualFriend.getMbti();
//        MbtiType mbtiType = MbtiType.valueOf(mbti);
//        String result = MbtiTraits.getTrait(mbtiType);
        String result = "너는 이제부터 MBTI중 " + mbti + "에 해당하는 사람이야. 답변은 10~30자 내외로 대답해줘.";
//                너는 이제부터 MBTI 중 ENFP에 해당하는 사람이야. 답변은 10~30자 내외로 대답해줘.

        StringBuilder temp = new StringBuilder();
        if(virtualFriend.getName() != null) temp.append(" 너의 이름은 " + virtualFriend.getName() +"이야. 명심해!");
        if(virtualFriend.getAge() != 0) temp.append(" 너의 나이는 " + virtualFriend.getAge() + "이야. ");
        if(virtualFriend.getGender() != null) temp.append(" 너의 성별은 " + virtualFriend.getGender() + "이야. ");
        if(virtualFriend.getRelationship() != null) temp.append("너와 나의 관계는 " + virtualFriend.getRelationship() + "이야");

        result += temp.toString();
        return result;

    }

    public String messageRequest(String content){
        com.mbtips.clova.dto.Message message = new com.mbtips.clova.dto.Message("user", content);
        ChatRequest chatRequest = new ChatRequest(Arrays.asList(message), MAX_TOKEN_VALUE);
        String apiResult = clovaApiFeignClient.getResponse(
                "Bearer " + clovaApiKeyProvider.apiKey(),
                UUID.randomUUID().toString(),
                chatRequest
        );

        log.debug("clovaResponse : {}" , apiResult);
        String resultResponse = "";
        try {
            ClovaApiResponse clovaApiResponse = objectMapper.readValue(apiResult, ClovaApiResponse.class);
            resultResponse = clovaApiResponse.getResult().getMessage().getContent();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return resultResponse;
    }

}
