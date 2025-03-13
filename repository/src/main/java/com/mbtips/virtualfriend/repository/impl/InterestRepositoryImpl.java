package com.mbtips.virtualfriend.repository.impl;

import com.mbtips.domain.virtualfriend.Interest;
import com.mbtips.virtualfriend.entity.InterestEntity;
import com.mbtips.virtualfriend.interfaces.InterestRepository;
import com.mbtips.virtualfriend.repository.jpa.InterestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepository {

    private final InterestJpaRepository interestJpaRepository;

    @Override
    public void saveAll(Collection<Interest> interests){
        List<InterestEntity> interestEntities = interests.stream()
                .map(InterestEntity::new)
                .toList();
        interestJpaRepository.saveAll(interestEntities);
    }
}
