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
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageManager {

    private final MessageService messageService;
    private final ConversationService conversationService;
    private final ClovaApiFeignClient clovaApiFeignClient;
    private final ClovaApiKeyProvider clovaApiKeyProvider;
    private final ConversationRepository conversationRepository;
    private final ObjectMapper objectMapper;

    public String sendMessage(User user, CreateMessageRequestDto createMessageRequestDto) {
        Conversation conversation = conversationService.findById(createMessageRequestDto.conversationId());


        Message requestMessage = Message.builder()
                .user(user)
                .conversation(conversation)
//                .virtualFriend(conversation.getVirtualFriend())
                .messageContent(createMessageRequestDto.messageContent())
                .build();

        messageService.saveMessage(requestMessage);

        String prompt = makePrompt(createMessageRequestDto.conversationId());
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

    private String makePrompt(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId);
        VirtualFriend virtualFriend = conversation.getVirtualFriend();
        // 대화방 기록
        // 가상친구 특성
        String mbti = virtualFriend.getMbti();
        MbtiType mbtiType = MbtiType.valueOf(mbti);
        String result = MbtiTraits.getTrait(mbtiType);
        // 가상친구 관심사
        result += ". 이제 대화를 시작해보자!";
        return result;

    }

    public String messageRequest(String content){
        com.mbtips.clova.dto.Message message = new com.mbtips.clova.dto.Message("user", content);
        ChatRequest chatRequest = new ChatRequest(Arrays.asList(message));
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
