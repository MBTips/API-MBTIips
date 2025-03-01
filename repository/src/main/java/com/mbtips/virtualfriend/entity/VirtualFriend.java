package com.mbtips.virtualfriend.entity;

import com.mbtips.user.entity.UserEntity;
import com.embitips.user.entity.UserEntity;
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
    @JoinColumns({
            @JoinColumn(name = "platform", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT)),
            @JoinColumn(name = "platform_id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    })
    private UserEntity userEntity;

    @Column(nullable = false)
    private String EorI;

    @Column(nullable = false)
    private String NorS;

    @Column(nullable = false)
    private String TorF;

    @Column(nullable = false)
    private String JorP;

    @Column(length = 20, nullable = false)
    private String virtualFriendName;

    @Column(nullable = false)
    private int virtualFriendAge;

    @Column(length = 4, nullable = false)
    private String virtualFriendSex;

    @Column(length = 20, nullable = false)
    private String virtualFriendRelationship;


}