package com.mbtips.message.repository.jpa;


import com.mbtips.conversation.entity.ConversationEntity;
import com.mbtips.domain.message.dto.response.GetMessageResponseDto;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.message.entity.MessageEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {

    @Query("select new com.mbtips.domain.message.dto.response.GetMessageResponseDto( " +
                "m.messageId , " +
                "m.messageContent , " +
                "m.sentAt , " +
                "m.user.userId , " +
                "m.virtualFriendEntity.virtualFriendId ) " +
            "from MessageEntity m " +
            "where m.conversationEntity.conversationId = :conversationId " +
            "ORDER BY m.sentAt asc ")
    List<GetMessageResponseDto> findByConversationId(Long conversationId);

    @Query("SELECT new com.mbtips.domain.message.dto.response.GetMessageResponseDto( " +
            "m.messageId, m.messageContent, m.sentAt, m.user.userId, m.virtualFriendEntity.virtualFriendId) " +
            "FROM MessageEntity m " +
            "WHERE m.conversationEntity.conversationId = :conversationId " +
            "ORDER BY m.sentAt desc ")
    List<GetMessageResponseDto> findRecentMessagesByConversationId(Long conversationId, PageRequest of);


    void deleteAllByConversationEntity(ConversationEntity conversationEntity);
}
