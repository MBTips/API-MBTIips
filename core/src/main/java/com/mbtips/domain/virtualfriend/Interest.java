package com.mbtips.domain.virtualfriend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

    private VirtualFriend virtualFriend;

    private String topic;
}
