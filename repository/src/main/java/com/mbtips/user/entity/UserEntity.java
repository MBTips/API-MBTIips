package com.mbtips.user.entity;

import com.mbtips.domain.user.User;
import com.mbtips.domain.user.enums.Platform;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    private String userId;

    public User toUser() {
        return User.builder()
                .userId(this.userId)
                .build();
    }

    public UserEntity(User user) {
        this.userId = user.getUserId();
    }
}