package com.mbtips.openChat.repository.jpa;

import com.mbtips.openChat.entity.OpenChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenChatJpaRepository extends JpaRepository<OpenChatEntity, Long> {

    List<OpenChatEntity> findAllByIsDeletedFalse();
}
