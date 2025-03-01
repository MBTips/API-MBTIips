package com.mbtips.conversation.entity;

import com.mbtips.virtualfriend.entity.VirtualFriend;
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
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conversationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "platform", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT)),
            @JoinColumn(name = "platform_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    })
    private UserEntity1 userEntity1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "virtual_friend_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private VirtualFriend virtualFriend;

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
}