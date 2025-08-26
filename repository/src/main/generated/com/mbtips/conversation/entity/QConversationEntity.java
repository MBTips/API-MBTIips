package com.mbtips.conversation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConversationEntity is a Querydsl query type for ConversationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConversationEntity extends EntityPathBase<ConversationEntity> {

    private static final long serialVersionUID = -561903174L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConversationEntity conversationEntity = new QConversationEntity("conversationEntity");

    public final NumberPath<Long> conversationId = createNumber("conversationId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.mbtips.user.entity.QUserEntity user;

    public final com.mbtips.virtualfriend.entity.QVirtualFriendEntity virtualFriendEntity;

    public QConversationEntity(String variable) {
        this(ConversationEntity.class, forVariable(variable), INITS);
    }

    public QConversationEntity(Path<? extends ConversationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConversationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConversationEntity(PathMetadata metadata, PathInits inits) {
        this(ConversationEntity.class, metadata, inits);
    }

    public QConversationEntity(Class<? extends ConversationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.mbtips.user.entity.QUserEntity(forProperty("user")) : null;
        this.virtualFriendEntity = inits.isInitialized("virtualFriendEntity") ? new com.mbtips.virtualfriend.entity.QVirtualFriendEntity(forProperty("virtualFriendEntity"), inits.get("virtualFriendEntity")) : null;
    }

}

