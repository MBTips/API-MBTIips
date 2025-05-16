package com.mbtips.virtualfriend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInterestId is a Querydsl query type for InterestId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QInterestId extends BeanPath<InterestId> {

    private static final long serialVersionUID = 644028695L;

    public static final QInterestId interestId = new QInterestId("interestId");

    public final StringPath topic = createString("topic");

    public final NumberPath<Long> virtualFriendId = createNumber("virtualFriendId", Long.class);

    public QInterestId(String variable) {
        super(InterestId.class, forVariable(variable));
    }

    public QInterestId(Path<? extends InterestId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterestId(PathMetadata metadata) {
        super(InterestId.class, metadata);
    }

}

