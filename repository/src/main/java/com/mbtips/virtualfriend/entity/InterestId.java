package com.mbtips.virtualfriend.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
public class InterestId implements Serializable {
    private Long virtualFriendId;
    private String topic;


}
