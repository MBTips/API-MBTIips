package com.mbtips.message.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessageEntity is a Querydsl query type for MessageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessageEntity extends EntityPathBase<MessageEntity> {

    private static final long serialVersionUID = 334791034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessageEntity messageEntity = new QMessageEntity("messageEntity");

    public final com.mbtips.conversation.entity.QConversationEntity conversationEntity;

    public final BooleanPath isRead = createBoolean("isRead");

    public final StringPath messageContent = createString("messageContent");

    public final NumberPath<Long> messageId = createNumber("messageId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> sentAt = createDateTime("sentAt", java.time.LocalDateTime.class);

    public final com.mbtips.user.entity.QUserEntity user;

    public final com.mbtips.virtualfriend.entity.QVirtualFriendEntity virtualFriendEntity;

    public QMessageEntity(String variable) {
        this(MessageEntity.class, forVariable(variable), INITS);
    }

    public QMessageEntity(Path<? extends MessageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessageEntity(PathMetadata metadata, PathInits inits) {
        this(MessageEntity.class, metadata, inits);
    }

    public QMessageEntity(Class<? extends MessageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conversationEntity = inits.isInitialized("conversationEntity") ? new com.mbtips.conversation.entity.QConversationEntity(forProperty("conversationEntity"), inits.get("conversationEntity")) : null;
        this.user = inits.isInitialized("user") ? new com.mbtips.user.entity.QUserEntity(forProperty("user")) : null;
        this.virtualFriendEntity = inits.isInitialized("virtualFriendEntity") ? new com.mbtips.virtualfriend.entity.QVirtualFriendEntity(forProperty("virtualFriendEntity"), inits.get("virtualFriendEntity")) : null;
    }

}

