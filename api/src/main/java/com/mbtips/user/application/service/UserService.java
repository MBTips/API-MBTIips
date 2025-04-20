package com.mbtips.user.application.service;

import com.mbtips.common.exception.CustomException;
import com.mbtips.domain.user.User;
import com.mbtips.domain.user.exception.UserException;
import com.mbtips.user.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User joinAndLogin(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseGet(() -> userRepository.save(new User(userId)));
    }

    public User findById(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserException.USER_NOT_FOUND));
    }

    public void delete(String userId) {
        userRepository.delete(userId);
    }
}
