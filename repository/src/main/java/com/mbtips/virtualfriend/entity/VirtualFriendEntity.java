package com.mbtips.virtualfriend.entity;

import com.mbtips.domain.user.User;
import com.mbtips.domain.virtualfriend.VirtualFriend;
import com.mbtips.domain.virtualfriend.enums.Gender;
import com.mbtips.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

@ToString
@Entity
@Getter
@Setter
@Table(name = "virtual_friend")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualFriendEntity {
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

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Gender virtualFriendSex;

    @Column(length = 30, nullable = false)
    private String friendType;

    @Column(length = 30, nullable = false)
    private String job;

    @Column(length = 100, nullable = true)
    private String freeSetting;

    public VirtualFriendEntity(VirtualFriend virtualFriend) {
        this.virtualFriendId = virtualFriend.getVirtualFriendId();
        this.user = new UserEntity(virtualFriend.getUser());
        this.mbti = virtualFriend.getMbti();
        this.virtualFriendName = virtualFriend.getName();
        this.virtualFriendAge = virtualFriend.getAge();
        this.virtualFriendSex = virtualFriend.getGender();
        this.friendType = virtualFriend.getFriendType();
        this.job = virtualFriend.getJob();
        this.freeSetting = virtualFriend.getFreeSetting();
    }

    public VirtualFriend toDomain() {
        return VirtualFriend.builder()
                .virtualFriendId(virtualFriendId)
                .user(user.toDomain())
                .name(virtualFriendName)
                .mbti(mbti)
                .age(virtualFriendAge)
                .gender(virtualFriendSex)
                .friendType(friendType)
                .job(job)
                .freeSetting(freeSetting)
                .build();
    }

}