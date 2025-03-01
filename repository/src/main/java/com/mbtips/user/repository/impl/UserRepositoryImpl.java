package com.mbtips.user.repository.impl;

import com.embitips.user.entity.UserEntity;
import com.embitips.user.entity.UserEntityId;
import com.mbtips.common.exception.CustomException;
import com.mbtips.domain.user.User;
import com.mbtips.domain.user.exception.UserException;
import com.mbtips.user.interfaces.UserRepository;
import com.mbtips.user.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        return userJpaRepository.save(userEntity)
                .toUser();
    }

    @Override
    public boolean isExistById(UserEntityId userId) {
        return userJpaRepository.existsById(userId);
    }

    @Override
    public User findById(UserEntityId userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserException.USER_NOT_FOUND))
                .toUser();
    }
}
