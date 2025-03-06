package com.mbtips.fastfriend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "fast_friend")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fastFriendId;

    @Column
    private String mbti;

    @Column(length = 20)
    private String fastFriendName;

    @Column
    private int fastFriendAge;

    @Column(length = 4, nullable = false)
    private String fastFriendSex;

    @Column(length = 20, nullable = false)
    private String fastFriendRelationship;
}
