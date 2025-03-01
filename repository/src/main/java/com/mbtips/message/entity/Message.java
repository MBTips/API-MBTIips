package com.mbtips.message.entity;

import com.mbtips.conversation.entity.Conversation;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "platform", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT)),
            @JoinColumn(name = "platform_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    })
    private UserEntity1 userEntity1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "virtual_friend_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private VirtualFriend virtualFriend;

    @Column(length = 500, nullable = false)
    private String messageContent;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    private Boolean isRead;

}