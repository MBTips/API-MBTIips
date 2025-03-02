package com.mbtips.domain.conversation;

import com.mbtips.conversation.ConversationRepository;
import com.mbtips.conversation.entity.Conversation;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationService {

    private final ConversationRepository conversationRepository;
    public Conversation createConversation(VirtualFriend friend, UserEntity userEntity) {
        Conversation conversation = Conversation.builder()
                .virtualFriend(friend)
                .user(userEntity).build();
        Conversation saveConversation = conversationRepository.save(conversation);
        return saveConversation;
    }

    public void deleteConversation(VirtualFriend friend) {
        Conversation conversation = conversationRepository.findByFriendId(friend);
        conversationRepository.delete(conversation);
        log.debug("{} 대화방이 삭제되었습니다.", conversation.getConversationId());
    }
}
