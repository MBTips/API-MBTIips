package com.mbtips.virtualfriend.repository.jpa;

import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VirtualFriendJpaRepository extends JpaRepository<VirtualFriendEntity, Long> {

    @Query("SELECT v, c.conversationId " +
            "FROM VirtualFriendEntity v " +
            "LEFT JOIN ConversationEntity c " +
            "ON v.user.userId = c.user.userId " +
            "AND v.virtualFriendId = c.virtualFriendEntity.virtualFriendId " +
            "WHERE v.user.userId = :userId")
    List<Object[]> findVirtualFriendAndConversation(String userId);

}
