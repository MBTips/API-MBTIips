package com.mbtips.user.entity;

import com.mbtips.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    private String userId;

    public User toDomain() {
        return User.builder()
                .userId(this.userId)
                .build();
    }

    public UserEntity(User user) {
        this.userId = user.getUserId();
    }
}