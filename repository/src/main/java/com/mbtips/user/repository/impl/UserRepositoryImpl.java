package com.mbtips.user.repository.impl;

import com.mbtips.domain.user.User;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.user.interfaces.UserRepository;
import com.mbtips.user.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User save(User user) {
        return userJpaRepository.save(new UserEntity(user))
                .toUser();
    }

    public Optional<User> findById(String userId) {
        return userJpaRepository.findById(userId)
                .map(UserEntity::toUser);
    }
}
