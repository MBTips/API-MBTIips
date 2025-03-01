package com.mbtips.user.application.service;

import com.embitips.user.entity.UserEntityId;
import com.mbtips.domain.user.User;
import com.mbtips.domain.user.enums.Platform;
import com.mbtips.user.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(UserEntityId id) {
        return userRepository.findById(id);
    }

    public boolean isExist(UserEntityId userId) {
        return userRepository.isExistById(userId);
    }

    public User save(Platform platform, long platformId) {
        User user = User.builder()
                .platform(platform)
                .platformId(platformId)
                .build();
        return userRepository.save(user);
    }
}
