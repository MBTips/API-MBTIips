package com.mbtips.user.repository.jpa;

import com.mbtips.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String>{
}
