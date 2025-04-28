package com.mbtips.domain.message;

import com.mbtips.domain.converstation.Conversation;
import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class Message {

    private Long messageId;

    private Conversation conversation;

    private User user;

    private VirtualFriend virtualFriend;

    private String messageContent;

    private LocalDateTime sendDateTime;

    private Boolean isRead;

}
