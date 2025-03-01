package com.embitips.user.entity;

import com.mbtips.domain.user.enums.Platform;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntityId {

    private Platform platform;

    private long platformId;
}
