package com.mbtips.virtualfriend.entity;

import com.mbtips.domain.virtualfriend.Interest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interest")
public class InterestEntity {

    @EmbeddedId
    private InterestId interestId;

    @MapsId("virtualFriendId")
    @ManyToOne
    @JoinColumn(name = "virtual_friend_id")
    private VirtualFriendEntity virtualFriendEntity;

    public InterestEntity(Interest interest) {
        interestId = InterestId.builder()
                .virtualFriendId(interest.getVirtualFriend().getVirtualFriendId())
                .topic(interest.getTopic())
                .build();
        virtualFriendEntity = new VirtualFriendEntity(interest.getVirtualFriend());
    }

    public Interest toDomain() {
        return Interest.builder()
                .virtualFriend(virtualFriendEntity.toDomain())
                .topic(interestId.getTopic())
                .build();

    }
}
