package com.mbtips.message;


import com.mbtips.message.entity.Message;
import com.mbtips.message.response.MessageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select new com.mbtips.message.response.MessageResponse(m.messageId, m.messageContent, m.sentAt, m.user.userId, m.virtualFriend.virtualFriendId) " +
            "from Message m where m.conversation.conversationId = :conversationId")
    List<MessageResponse> findByConversationId(Long conversationId);
}
