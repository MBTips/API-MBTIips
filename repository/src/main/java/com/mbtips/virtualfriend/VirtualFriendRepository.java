package com.mbtips.virtualfriend;

import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VirtualFriendRepository extends JpaRepository<VirtualFriend, Long> {

    @Query("select v from VirtualFriend v where v.user.userId = :userId")
    List<VirtualFriend> findByUserId(Long userId);

    @Query("select v from VirtualFriend v where v.virtualFriendId = :friendId and v.user = :user")
    Optional<VirtualFriend> findByFriendId(Long friendId, User user);
}
