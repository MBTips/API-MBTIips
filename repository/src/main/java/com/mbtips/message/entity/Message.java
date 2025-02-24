package com.mbtips.message.entity;

import com.mbtips.conversation.entity.Conversation;
import com.mbtips.user.entity.User;
import com.mbtips.virtualfriend.entity.VirtualFriend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "virtual_friend_id", nullable = false)
    private VirtualFriend virtualFriend;

    @Column(length = 500, nullable = false)
    private String messageContent;

    @Column(nullable = false)
    private LocalDateTime
            sentAt;

    @Column(nullable = false)
    private Boolean isRead;

}