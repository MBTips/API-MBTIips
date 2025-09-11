package com.mbtips.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbtips.common.constant.Constant;
import com.mbtips.common.exception.CustomException;
import com.mbtips.domain.openChat.exception.OpenChatException;
import com.mbtips.openChat.application.dto.OpenChatMessageDto;
import com.mbtips.openChat.application.service.OpenChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final OpenChatMessageService openChatMessageService;

    private static final String NICKNAME = "nickname";
    private static final String OPEN_CHAT_ID = "open_chat_id";

    public static final Map<Long, Set<WebSocketSession>> webSocketSessionMap = new ConcurrentHashMap<>();

    // 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();
        log.info("{} connected", query);

        if (query == null) {
            log.error("not found Query");
            throw new IllegalArgumentException("Not Found Session Query");
        }

        Map<String, String> queryParamMap = this.parseQueryParam(query);
        long openChatId = Long.parseLong(queryParamMap.get(OPEN_CHAT_ID));
        if (this.checkNickname(openChatId, queryParamMap.get(NICKNAME))) {
            throw new CustomException(OpenChatException.DUPLICATED_NICKNAME);
        }

        for (Map.Entry<String, String> entry : queryParamMap.entrySet()) {
            if (entry.getKey().equals(OPEN_CHAT_ID)) {
                continue;
            }
            session.getAttributes().put(entry.getKey(), entry.getValue());
        }
        webSocketSessionMap.computeIfAbsent(openChatId, k -> new HashSet<>()).add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = (String) message.getPayload();
        OpenChatMessageDto openChatMessageDto = objectMapper.readValue(payload, OpenChatMessageDto.class);
        Set<WebSocketSession> webSocketSessions = webSocketSessionMap.get(openChatMessageDto.openChatId());

        if (webSocketSessions == null) {
            log.error("메시지를 전송할 오픈채팅방이 없습니다. openChatId : {}", openChatMessageDto.openChatId());
            throw new CustomException(OpenChatException.NOT_FOUND_OPEN_CHAT);
        }

        openChatMessageService.send(openChatMessageDto);

        webSocketSessions.forEach(webSocketSession -> {
            try {
                webSocketSession.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        String query = session.getUri().getQuery();
        log.info("{} disconnected", query);

        if (query == null) {
            log.error("not found Query");
            throw new IllegalArgumentException("Not Found Session Query");
        }

        Map<String, String> queryParamMap = this.parseQueryParam(query);
        long openChatId = Long.parseLong(queryParamMap.get(OPEN_CHAT_ID));
        Set<WebSocketSession> webSocketSessions = webSocketSessionMap.get(openChatId);

        if (webSocketSessions == null) {
            log.error("나가려는 오픈채팅방이 없습니다. openChatId : {}", openChatId);
            throw new CustomException(OpenChatException.NOT_FOUND_OPEN_CHAT);
        }
        webSocketSessions.removeIf(webSocketSession -> webSocketSession.getId().equals(session.getId()));
    }

    private Map<String, String> parseQueryParam(String query) {
        HashMap<String, String> queryParamMap = new HashMap<>();
        String[] pairs = query.split("&");
        Arrays.stream(pairs).forEach(pair -> {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                String value = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                queryParamMap.put(key, value);
            }
        });
        return queryParamMap;
    }

    private boolean checkNickname(long openChatId, String nickname) {
        Set<WebSocketSession> webSocketSessions = webSocketSessionMap.get(openChatId);
        if (webSocketSessions == null) {
            log.error("존재하지 않은 openChatId : {}", openChatId);
            throw new CustomException(OpenChatException.NOT_FOUND_OPEN_CHAT);
        }
        return webSocketSessions.stream()
                .anyMatch(webSocketSession -> webSocketSession.getAttributes().get(NICKNAME).equals(nickname));
    }
}
