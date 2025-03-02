package com.mbtips.fastfriend;

import com.mbtips.fastfriend.entity.FastFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFriendRepository extends JpaRepository<FastFriend, Long> {

}
