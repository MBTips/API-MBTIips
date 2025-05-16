package com.mbtips.virtualfriend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVirtualFriendEntity is a Querydsl query type for VirtualFriendEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVirtualFriendEntity extends EntityPathBase<VirtualFriendEntity> {

    private static final long serialVersionUID = 755307482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVirtualFriendEntity virtualFriendEntity = new QVirtualFriendEntity("virtualFriendEntity");

    public final StringPath friendType = createString("friendType");

    public final StringPath mbti = createString("mbti");

    public final com.mbtips.user.entity.QUserEntity user;

    public final NumberPath<Integer> virtualFriendAge = createNumber("virtualFriendAge", Integer.class);

    public final NumberPath<Long> virtualFriendId = createNumber("virtualFriendId", Long.class);

    public final StringPath virtualFriendName = createString("virtualFriendName");

    public final StringPath virtualFriendRelationship = createString("virtualFriendRelationship");

    public final EnumPath<com.mbtips.domain.virtualfriend.enums.Gender> virtualFriendSex = createEnum("virtualFriendSex", com.mbtips.domain.virtualfriend.enums.Gender.class);

    public QVirtualFriendEntity(String variable) {
        this(VirtualFriendEntity.class, forVariable(variable), INITS);
    }

    public QVirtualFriendEntity(Path<? extends VirtualFriendEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVirtualFriendEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVirtualFriendEntity(PathMetadata metadata, PathInits inits) {
        this(VirtualFriendEntity.class, metadata, inits);
    }

    public QVirtualFriendEntity(Class<? extends VirtualFriendEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.mbtips.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

