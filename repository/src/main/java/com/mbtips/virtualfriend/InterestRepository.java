package com.mbtips.virtualfriend;

import com.mbtips.virtualfriend.entity.Interest;
import com.mbtips.virtualfriend.entity.InterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, InterestId> {
}
