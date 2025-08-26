package com.mbtips.openChat.entity;

import com.mbtips.common.converter.BooleanToYnConverter;
import com.mbtips.domain.openChat.OpenChat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "open_chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long openChatId;

    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Convert(converter = BooleanToYnConverter.class)
    private boolean isDeleted;

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

    public OpenChat toDomain() {
        return OpenChat.builder()
                .openChatId(openChatId)
                .imageUrl(imageUrl)
                .title(title)
                .description(description)
                .isDeleted(isDeleted)
                .build();
    }

    public OpenChatEntity(OpenChat openChat) {
        this.openChatId = openChat.getOpenChatId();
        this.imageUrl = openChat.getImageUrl();
        this.title = openChat.getTitle();
        this.description = openChat.getDescription();
        this.isDeleted = openChat.isDeleted();
    }

}
