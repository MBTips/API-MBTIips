package com.mbtips.virtualfriend.repository.impl;

import com.mbtips.domain.virtualfriend.Interest;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.virtualfriend.entity.InterestEntity;
import com.mbtips.virtualfriend.interfaces.InterestRepository;
import com.mbtips.virtualfriend.repository.jpa.InterestJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepository {

    private final InterestJpaRepository interestJpaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveAll(Collection<Interest> interests){
        List<InterestEntity> interestEntities = interests.stream()
                .map(InterestEntity::new)
                .toList();
        interestJpaRepository.saveAll(interestEntities);
    }

    @Override
    @Transactional
    public void deleteInterestByVirtualFriend(VirtualFriend virtualFriend) {
        interestJpaRepository.deleteByVirtualFriendId(virtualFriend.getVirtualFriendId());
    }

    @Override
    public List<String> findTopicsByVirtualFriendId(Long virtualFriendId) {
        return interestJpaRepository.findTopicsByVirtualFriendId(virtualFriendId);
    }
}
