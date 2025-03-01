package com.mbtips.virtualfriend;

import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VirtualFriendRepository extends JpaRepository<VirtualFriend, Long> {

    @Query("SELECT v, c.conversationId " +
            "FROM VirtualFriend v " +
            "LEFT JOIN Conversation c " +
            "ON v.user.userId = c.user.userId " +
            "AND v.virtualFriendId = c.virtualFriend.virtualFriendId " +
            "WHERE v.user.userId = :userId")
    List<Object[]> findvirtualFriendAndConversation(Long userId);


    @Query("select v from VirtualFriend v where v.virtualFriendId = :friendId and v.user = :user")
    Optional<VirtualFriend> findByFriendId(Long friendId, UserEntity userEntity);
}
