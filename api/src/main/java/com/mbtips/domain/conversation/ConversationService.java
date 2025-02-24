package com.mbtips.domain.conversation;

import com.mbtips.conversation.ConversationRepository;
import com.mbtips.conversation.entity.Conversation;
import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    public Conversation createConversation(VirtualFriend friend, User user) {
        Conversation conversation = Conversation.builder()
                .virtualFriend(friend)
                .user(user).build();
        Conversation saveConversation = conversationRepository.save(conversation);
        return saveConversation;
    }
}
