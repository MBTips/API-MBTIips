package com.mbtips.fastfriend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFastFriend is a Querydsl query type for FastFriend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFastFriend extends EntityPathBase<FastFriend> {

    private static final long serialVersionUID = -60125115L;

    public static final QFastFriend fastFriend = new QFastFriend("fastFriend");

    public final NumberPath<Integer> fastFriendAge = createNumber("fastFriendAge", Integer.class);

    public final NumberPath<Long> fastFriendId = createNumber("fastFriendId", Long.class);

    public final StringPath fastFriendName = createString("fastFriendName");

    public final StringPath fastFriendRelationship = createString("fastFriendRelationship");

    public final EnumPath<com.mbtips.domain.virtualfriend.enums.Gender> fastFriendSex = createEnum("fastFriendSex", com.mbtips.domain.virtualfriend.enums.Gender.class);

    public final StringPath mbti = createString("mbti");

    public QFastFriend(String variable) {
        super(FastFriend.class, forVariable(variable));
    }

    public QFastFriend(Path<? extends FastFriend> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFastFriend(PathMetadata metadata) {
        super(FastFriend.class, metadata);
    }

}

