package com.mbtips.conversation.repository.jpa;

import com.mbtips.conversation.entity.ConversationEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ConversationJpaRepository extends JpaRepository<ConversationEntity, Long> {

    @Query("select c from ConversationEntity c where c.virtualFriendEntity = :virtualFriend")
    Optional<ConversationEntity> findByVirtualFriend(VirtualFriendEntity virtualFriend);
}
