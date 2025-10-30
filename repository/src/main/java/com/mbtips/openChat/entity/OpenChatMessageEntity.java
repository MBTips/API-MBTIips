package com.mbtips.openChat.entity;

import com.mbtips.domain.openChat.OpenChatMessage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "open_chat_message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long openChatMessageId;

    private Long openChatId;

    private String nickname;

    private String mbti;

    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public OpenChatMessage toDomain() {
        return OpenChatMessage.builder()
                .openChatMessageId(openChatMessageId)
                .openChatId(openChatId)
                .nickname(nickname)
                .mbti(mbti)
                .message(message)
                .build();
    }

    public OpenChatMessageEntity(OpenChatMessage openChatMessage) {
        this.openChatMessageId = openChatMessage.getOpenChatMessageId();
        this.openChatId = openChatMessage.getOpenChatId();
        this.mbti = openChatMessage.getMbti();
        this.nickname = openChatMessage.getNickname();
        this.message = openChatMessage.getMessage();
    }
}
