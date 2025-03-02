package com.mbtips.user.interfaces;

import com.mbtips.domain.user.User;
import com.mbtips.domain.user.enums.Platform;
import com.mbtips.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(String userId);
}
