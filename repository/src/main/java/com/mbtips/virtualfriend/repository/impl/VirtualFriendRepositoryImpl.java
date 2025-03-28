package com.mbtips.virtualfriend.repository.impl;

import com.mbtips.common.exception.CustomException;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.domain.virtualfriend.exception.VirtualFriendException;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import com.mbtips.virtualfriend.interfaces.VirtualFriendRepository;
import com.mbtips.virtualfriend.repository.jpa.VirtualFriendJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VirtualFriendRepositoryImpl implements VirtualFriendRepository {

    private final VirtualFriendJpaRepository virtualFriendJpaRepository;


    @Override
    public VirtualFriend save(VirtualFriend virtualFriend) {
        VirtualFriendEntity virtualFriendEntity = new VirtualFriendEntity(virtualFriend);
        return virtualFriendJpaRepository.save(virtualFriendEntity)
                .toDomain();
    }

    @Override
    public void delete(VirtualFriend virtualFriend) {
        VirtualFriendEntity virtualFriendEntity = new VirtualFriendEntity(virtualFriend);
        virtualFriendJpaRepository.delete(virtualFriendEntity);
    }

    @Override
    public VirtualFriend findById(long virtualFriendId) {
        return virtualFriendJpaRepository.findById(virtualFriendId)
                .orElseThrow(() -> new CustomException(VirtualFriendException.NOT_FOUND))
                .toDomain();
    }

    @Override
    public List<Object[]> findVirtualFriendAndConversation(String userId) {
        return virtualFriendJpaRepository.findVirtualFriendAndConversation(userId);
    }

    @Override
    public VirtualFriend update(Long virtualFriendId, VirtualFriend virtualFriend) {
        VirtualFriendEntity entity = virtualFriendJpaRepository.findById(virtualFriendId).orElseThrow(() ->
                new CustomException(VirtualFriendException.NOT_FOUND));

        entity.setMbti(virtualFriend.getMbti());
        entity.setVirtualFriendAge(virtualFriend.getAge());
        entity.setVirtualFriendName(virtualFriend.getName());
        entity.setVirtualFriendSex(virtualFriend.getGender());
        entity.setVirtualFriendRelationship(virtualFriend.getRelationship());

        VirtualFriendEntity updateEntity = virtualFriendJpaRepository.save(entity);
        return updateEntity.toDomain();
    }


}
