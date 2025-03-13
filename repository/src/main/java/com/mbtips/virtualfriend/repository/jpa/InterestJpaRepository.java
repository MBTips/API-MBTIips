package com.mbtips.virtualfriend.repository.jpa;

import com.mbtips.virtualfriend.entity.InterestEntity;
import com.mbtips.virtualfriend.entity.InterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestJpaRepository extends JpaRepository<InterestEntity, InterestId> {
}
