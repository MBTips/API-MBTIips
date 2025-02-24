package com.mbtips.virtualfriend.entity;

import com.mbtips.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "virtual_friend")
@Builder
public class VirtualFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long virtualFriendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String eandi;

    @Column(nullable = false)
    private String nands;

    @Column(nullable = false)
    private String tandf;

    @Column(nullable = false)
    private String jandp;

    @Column(length = 20)
    private String virtualFriendName;

    @Column
    private int virtualFriendAge;

    @Column(length = 4, nullable = false)
    private String virtualFriendSex;

    @Column(length = 20, nullable = false)
    private String virtualFriendRelationship;


}