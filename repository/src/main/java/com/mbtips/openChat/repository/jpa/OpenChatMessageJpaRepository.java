package com.mbtips.openChat.repository.jpa;

import com.mbtips.openChat.entity.OpenChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenChatMessageJpaRepository extends JpaRepository<OpenChatMessageEntity, Long> {
}
