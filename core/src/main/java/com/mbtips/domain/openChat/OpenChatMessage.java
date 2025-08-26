package com.mbtips.domain.openChat;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OpenChatMessage {

    private Long openChatMessageId;

    private Long openChatId;

    private String nickname;

    private String mbti;

    private String message;

}
