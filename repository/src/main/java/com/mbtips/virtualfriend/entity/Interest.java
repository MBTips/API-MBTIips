package com.mbtips.virtualfriend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

    @EmbeddedId
    private InterestId interestId;

    @MapsId("virtualFriendId")
    @ManyToOne
    @JoinColumn(name = "virtual_friend_id")
    private VirtualFriend virtualFriend;

}
