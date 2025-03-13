package com.mbtips.virtualfriend.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class InterestId implements Serializable {
    private Long virtualFriendId;
    private String topic;
}
