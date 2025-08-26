package com.mbtips.virtualfriend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestEntity is a Querydsl query type for InterestEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestEntity extends EntityPathBase<InterestEntity> {

    private static final long serialVersionUID = -1738370593L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestEntity interestEntity = new QInterestEntity("interestEntity");

    public final QInterestId interestId;

    public final QVirtualFriendEntity virtualFriendEntity;

    public QInterestEntity(String variable) {
        this(InterestEntity.class, forVariable(variable), INITS);
    }

    public QInterestEntity(Path<? extends InterestEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestEntity(PathMetadata metadata, PathInits inits) {
        this(InterestEntity.class, metadata, inits);
    }

    public QInterestEntity(Class<? extends InterestEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interestId = inits.isInitialized("interestId") ? new QInterestId(forProperty("interestId")) : null;
        this.virtualFriendEntity = inits.isInitialized("virtualFriendEntity") ? new QVirtualFriendEntity(forProperty("virtualFriendEntity"), inits.get("virtualFriendEntity")) : null;
    }

}

