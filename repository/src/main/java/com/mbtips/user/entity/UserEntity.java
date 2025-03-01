package com.mbtips.user.entity;

import com.mbtips.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @EmbeddedId
    private UserEntityId userId;

    public User toUser() {
        return User.builder()
                .platform(this.userId.getPlatform())
                .platformId(this.userId.getPlatformId())
                .build();
    }

    public UserEntity(User user) {
        this.userId = UserEntityId.builder()
                .platform(user.getPlatform())
                .platformId(user.getPlatformId())
                .build();
    }
}