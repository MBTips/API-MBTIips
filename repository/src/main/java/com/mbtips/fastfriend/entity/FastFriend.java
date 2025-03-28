package com.mbtips.fastfriend.entity;

import com.mbtips.domain.virtualfriend.enums.Gender;
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

    @Column(nullable = false)
    private String mbti;

    @Column(length = 20)
    private String fastFriendName;

    @Column
    private int fastFriendAge;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Gender fastFriendSex;

    @Column(length = 20)
    private String fastFriendRelationship;
}
