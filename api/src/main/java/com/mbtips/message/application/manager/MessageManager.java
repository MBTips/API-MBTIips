package com.mbtips.message.application.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.clova.client.ClovaApiFeignClient;
import com.mbtips.clova.dto.ChatRequest;
import com.mbtips.clova.dto.ClovaApiResponse;
import com.mbtips.common.provider.ClovaApiKeyProvider;
import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.request.CreateMessageRequestDto;
import com.mbtips.domain.user.User;
import com.mbtips.fastfriend.entity.FastFriend;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageManager {

    private final MessageService messageService;
    private final ConversationService conversationService;
    private final ClovaApiFeignClient clovaApiFeignClient;
    private final ClovaApiKeyProvider clovaApiKeyProvider;
    private final ObjectMapper objectMapper;

    public void sendMessage(User user, CreateMessageRequestDto createMessageRequestDto) {
        Conversation conversation = conversationService.findById(createMessageRequestDto.conversationId());


        Message requestMessage = Message.builder()
                .user(user)
                .conversation(conversation)
                .virtualFriend(conversation.getVirtualFriend())
                .messageContent(createMessageRequestDto.messageContent())
                .build();

        messageService.saveMessage(requestMessage);

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
