package com.mbtips.domain.converstation;

import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Conversation {

    private Long conversationId;

    private User user;

    private VirtualFriend virtualFriend;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;
}
