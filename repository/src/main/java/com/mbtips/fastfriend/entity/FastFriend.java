package com.mbtips.fastfriend.entity;

import com.mbtips.user.entity.User;
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
    private String EorI;

    @Column(nullable = false)
    private String NorS;

    @Column(nullable = false)
    private String TorF;

    @Column(nullable = false)
    private String JorP;

    @Column(length = 20)
    private String fastFriendName;

    @Column
    private int fastFriendAge;

    @Column(length = 4, nullable = false)
    private String fastFriendSex;

    @Column(length = 20, nullable = false)
    private String fastFriendRelationship;
}
