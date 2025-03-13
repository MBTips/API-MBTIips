package com.mbtips.conversation.entity;

import com.mbtips.domain.converstation.Conversation;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

@Entity
@Getter
@Setter
@Table(name = "conversation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conversationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "virtual_friend_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private VirtualFriendEntity virtualFriendEntity;

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

    public ConversationEntity(Conversation conversation){
        this.conversationId = conversation.getConversationId();
        this.user = new UserEntity(conversation.getUser());
        this.virtualFriendEntity = new VirtualFriendEntity(conversation.getVirtualFriend());
    }

    public Conversation toDomain() {
        return Conversation.builder()
                .conversationId(conversationId)
                .user(user.toDomain())
                .virtualFriend(virtualFriendEntity.toDomain())
                .build();
    }
}