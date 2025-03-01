package com.mbtips.user.repository.jpa;

import com.embitips.user.entity.UserEntity;
import com.embitips.user.entity.UserEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, UserEntityId>{

}
