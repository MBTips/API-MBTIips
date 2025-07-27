package com.mbtips.message.application.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.clova.client.ClovaApiFeignClient;
import com.mbtips.clova.dto.ChatRequest;
import com.mbtips.common.provider.ClovaApiKeyProvider;
import com.mbtips.common.provider.GptApiKeyProvider;
import com.mbtips.conversation.interfaces.ConversationRepository;
import com.mbtips.domain.conversation.service.ConversationService;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.message.Message;
import com.mbtips.domain.message.dto.request.CreateMessageRequestDto;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.gpt.client.GptApiFeignClient;
import com.mbtips.gpt.dto.GptChatRequest;
import com.mbtips.gpt.dto.GptResponse;
import com.mbtips.message.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    private final GptApiFeignClient gptApiFeignClient;
    private final GptApiKeyProvider gptApiKeyProvider;

    @Transactional
    public String sendMessage(User user, CreateMessageRequestDto dto) {
        Conversation conversation = conversationService.findById(dto.conversationId());
        List<GetMessageResponseDto> recentMessages = messageService.getRecentMessagesOfConversationId(conversation.getConversationId());

        // 사용자 메시지 저장
        Message userMessage = Message.createMessage(user, conversation, dto.messageContent());
        messageService.saveMessage(userMessage);

        // 프롬프트 생성
        String prompt = buildFullPrompt(conversation, recentMessages, dto.messageContent());
        log.debug("Final Prompt: {}", prompt);

        // 메시지 응답 요청
        String responseContent = messageRequest(prompt);
        log.debug("Response Message: {}", responseContent);


        Message responseMessage = Message.createMessage(conversation, responseContent);
        messageService.saveMessage(responseMessage);

        return responseContent;
    }

    private String buildFullPrompt(Conversation conversation, List<GetMessageResponseDto> recentMessages, String userMessage) {
        String basePrompt = makePrompt(conversation.getConversationId());
        String recentMessageSummary = makeRecentMessageString(recentMessages);

        log.debug("Base Prompt: {}", basePrompt);
        log.debug("Recent Messages: {}", recentMessageSummary);

        String mbtiStyle = conversation.getVirtualFriend().getMbti();
        String suffix = " 이제 대화를 시작해보자!";
        String instruction = "이제 들어온 톡은 아래와 같은데 " + mbtiStyle + "스타일 친구 같이 무례하지 않은 반말로 대답해봐";

        return basePrompt + recentMessageSummary + suffix + " " + instruction + " " + userMessage;
    }

    private String makeRecentMessageString(List<GetMessageResponseDto> messages) {
        Collections.reverse(messages);
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(" 지금까지 너와 상대방은 이런 대화를 해왔어 ");
        int idx = messages.size() - 6;
        if(idx < 0) idx = 0;
        for(int i = idx; i < messages.size(); i++){
            if(messages.get(i).userId() != null) {
                messageBuilder.append(" 상대방 : ");
                messageBuilder.append(messages.get(i).messageContent());
            }
            if(messages.get(i).virtualFriendId() != null) {
                messageBuilder.append(" 너 : ");
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
        String result = "지금부터 너는 " + mbti + "스타일의 친구야.  너의 페르소나에 몰입해서 상대방과 대화해야 해.";

        StringBuilder temp = new StringBuilder();
        if(virtualFriend.getName() != null) temp.append(" 너의 이름은 " + virtualFriend.getName() +"야.");
        if(virtualFriend.getAge() != 0) temp.append(" 너의 나잇대는 " + virtualFriend.getAge() + "야.");
        if(virtualFriend.getGender() != null) temp.append(" 너의 성별은 " + virtualFriend.getGender() + "이야.");
//        if(virtualFriend.getRelationship() != null) temp.append(" 너와 나의 관계는 " + virtualFriend.getRelationship() + "이야");

        result += temp.toString();
        return result;

    }

    public String messageRequest(String content){
        com.mbtips.clova.dto.Message message = new com.mbtips.clova.dto.Message("user", content);
        ChatRequest chatRequest = new ChatRequest(Arrays.asList(message), MAX_TOKEN_VALUE);
//        String apiResult = clovaApiFeignClient.getResponse(
//                "Bearer " + clovaApiKeyProvider.apiKey(),
//                UUID.randomUUID().toString(),
//                chatRequest
//        );
//        List<com.mbtips.clova.dto.Message> messages = List.of(
//                new com.mbtips.clova.dto.Message("user", "Write a poem about coffee.")
//        );
        List<com.mbtips.clova.dto.Message> messages = List.of(
                new com.mbtips.clova.dto.Message("user", content)
        );
        String response = gptApiFeignClient.getResponse(
                "Bearer " + gptApiKeyProvider.apiKey(),
                GptChatRequest.builder()
                        .model("gpt-4o-mini")
                        .messages(messages)
                        .temperature(0.7)
                        .max_tokens(100)
                        .build()
        );

        String resultResponse = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GptResponse gptResponse = objectMapper.readValue(response, GptResponse.class);
            resultResponse = gptResponse.choices.get(0).message.content;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse GPT response", e);
        }
        log.debug("gptResposne : {}", resultResponse);
        return resultResponse;
    }

    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> resultList = null;

        String response = gptApiFeignClient.getModelList(
                "Bearer " + gptApiKeyProvider.apiKey()
        );
        System.out.println(resultList);
        return resultList;
    }

}
