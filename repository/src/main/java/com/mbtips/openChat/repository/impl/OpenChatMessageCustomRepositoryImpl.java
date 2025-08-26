package com.mbtips.openChat.repository.impl;

import com.mbtips.openChat.entity.OpenChatMessageEntity;
import com.mbtips.openChat.entity.QOpenChatMessageEntity;
import com.mbtips.openChat.repository.jpa.OpenChatMessageCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpenChatMessageCustomRepositoryImpl implements OpenChatMessageCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<OpenChatMessageEntity> findNextOpenChatMessages(Long openChatId, Long openChatMessageId) {
        QOpenChatMessageEntity openChatMessage = QOpenChatMessageEntity.openChatMessageEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(openChatMessage.openChatId.eq(openChatId));

        if (openChatMessageId != null && openChatMessageId > 0) {
            builder.and(openChatMessage.openChatMessageId.lt(openChatMessageId));
        }

        return jpaQueryFactory
                .selectFrom(openChatMessage)
                .where(builder)
                .orderBy(openChatMessage.openChatMessageId.desc())
                .limit(10)
                .fetch();
    }
}
