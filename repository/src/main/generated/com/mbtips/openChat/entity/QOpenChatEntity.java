package com.mbtips.openChat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOpenChatEntity is a Querydsl query type for OpenChatEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpenChatEntity extends EntityPathBase<OpenChatEntity> {

    private static final long serialVersionUID = -406842952L;

    public static final QOpenChatEntity openChatEntity = new QOpenChatEntity("openChatEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> openChatId = createNumber("openChatId", Long.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QOpenChatEntity(String variable) {
        super(OpenChatEntity.class, forVariable(variable));
    }

    public QOpenChatEntity(Path<? extends OpenChatEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOpenChatEntity(PathMetadata metadata) {
        super(OpenChatEntity.class, metadata);
    }

}

