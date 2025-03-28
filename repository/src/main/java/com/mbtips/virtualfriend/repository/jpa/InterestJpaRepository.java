package com.mbtips.virtualfriend.repository.jpa;

import com.mbtips.virtualfriend.entity.InterestEntity;
import com.mbtips.virtualfriend.entity.InterestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestJpaRepository extends JpaRepository<InterestEntity, InterestId> {

    @Query("select i.interestId.topic from InterestEntity i where i.virtualFriendEntity.virtualFriendId = :virtualFriendId")
    List<String> findTopicsByVirtualFriendId(Long virtualFriendId);

    @Modifying
    @Query("delete from InterestEntity i where i.interestId.virtualFriendId = :virtualFriendId")
    void deleteByVirtualFriendId(@Param("virtualFriendId") Long virtualFriendId);
}
