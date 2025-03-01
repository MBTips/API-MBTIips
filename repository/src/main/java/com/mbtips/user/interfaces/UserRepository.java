package com.mbtips.user.interfaces;

import com.embitips.user.entity.UserEntityId;
import com.mbtips.domain.user.User;

public interface UserRepository {

    User save(User user);

    boolean isExistById(UserEntityId userId);

    User findById(UserEntityId userId);
}
