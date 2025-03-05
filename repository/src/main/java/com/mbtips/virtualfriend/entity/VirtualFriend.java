package com.mbtips.virtualfriend.entity;

import com.mbtips.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

@Entity
@Getter
@Setter
@Table(name = "virtual_friend")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long virtualFriendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private UserEntity user;

    @Column(nullable = false)
    private String mbti;

    @Column(length = 20, nullable = false)
    private String virtualFriendName;

    @Column(nullable = false)
    private int virtualFriendAge;

    @Column(length = 4, nullable = false)
    private String virtualFriendSex;

    @Column(length = 20, nullable = false)
    private String virtualFriendRelationship;


}