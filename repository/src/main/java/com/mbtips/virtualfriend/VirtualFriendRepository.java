package com.mbtips.virtualfriend;

import com.mbtips.virtualfriend.entity.VirtualFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirtualFriendRepository extends JpaRepository<VirtualFriend, Long> {

    @Query("select v from VirtualFriend v where v.user.userId = :userId")
    List<VirtualFriend> findByUserId(Long userId);
}
