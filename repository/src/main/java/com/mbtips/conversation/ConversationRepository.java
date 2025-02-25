package com.mbtips.conversation;

import com.mbtips.conversation.entity.Conversation;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query("select c from Conversation c where c.virtualFriend = :friend")
    Conversation findByFriendId(VirtualFriend friend);
}
