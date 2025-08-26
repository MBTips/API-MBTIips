package com.mbtips.domain.openChat;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OpenChat {

    private Long openChatId;

    private String imageUrl;

    private String title;

    private String description;

    private boolean isDeleted;

}
