package com.mbtips.domain.conversation.service;

import com.mbtips.conversation.interfaces.ConversationRepository;
import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public Conversation findById(Long conversationId) {
        return conversationRepository.findById(conversationId);
    }

    public Conversation createConversation(VirtualFriend virtualFriend, User user) {
        Conversation conversation = Conversation.builder()
                .virtualFriend(virtualFriend)
                .user(user)
                .build();
        return conversationRepository.save(conversation);
    }

    public void deleteConversation(VirtualFriend virtualFriend) {
        Conversation conversation = conversationRepository.findByVirtualFriend(virtualFriend);
        conversationRepository.delete(conversation);
        log.debug("{} 대화방이 삭제되었습니다.", conversation.getConversationId());
    }
}
