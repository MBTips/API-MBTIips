package com.mbtips.message.entity;

import com.mbtips.conversation.entity.ConversationEntity;
import com.mbtips.domain.message.Message;
import com.mbtips.user.entity.UserEntity;
import com.mbtips.virtualfriend.entity.VirtualFriendEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

@Entity
@Getter
@Table(name = "message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private ConversationEntity conversationEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "virtual_friend_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private VirtualFriendEntity virtualFriendEntity;

    @Column(length = 500, nullable = false)
    private String messageContent;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    private Boolean isRead;

    public MessageEntity(Message message) {
        this.messageId = message.getMessageId();
        this.conversationEntity = new ConversationEntity(message.getConversation());
        if(message.getUser() != null ) this.user = new UserEntity(message.getUser());
        if(message.getVirtualFriend() != null)this.virtualFriendEntity = new VirtualFriendEntity(message.getVirtualFriend());
        this.messageContent = message.getMessageContent();
        this.sentAt = LocalDateTime.now();
    }

}