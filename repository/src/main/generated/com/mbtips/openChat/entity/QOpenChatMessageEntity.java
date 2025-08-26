package com.mbtips.openChat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOpenChatMessageEntity is a Querydsl query type for OpenChatMessageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpenChatMessageEntity extends EntityPathBase<OpenChatMessageEntity> {

    private static final long serialVersionUID = -1361774763L;

    public static final QOpenChatMessageEntity openChatMessageEntity = new QOpenChatMessageEntity("openChatMessageEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath mbti = createString("mbti");

    public final StringPath message = createString("message");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Long> openChatId = createNumber("openChatId", Long.class);

    public final NumberPath<Long> openChatMessageId = createNumber("openChatMessageId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QOpenChatMessageEntity(String variable) {
        super(OpenChatMessageEntity.class, forVariable(variable));
    }

    public QOpenChatMessageEntity(Path<? extends OpenChatMessageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOpenChatMessageEntity(PathMetadata metadata) {
        super(OpenChatMessageEntity.class, metadata);
    }

}

