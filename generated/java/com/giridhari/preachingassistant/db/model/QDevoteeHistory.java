package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDevoteeHistory is a Querydsl query type for DevoteeHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevoteeHistory extends EntityPathBase<DevoteeHistory> {

    private static final long serialVersionUID = -656267808L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDevoteeHistory devoteeHistory = new QDevoteeHistory("devoteeHistory");

    public final StringPath comment = createString("comment");

    public final QDevotee commentedByDevotee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDevotee ratedDevotee;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final EnumPath<com.giridhari.preachingassistant.model.Response> response = createEnum("response", com.giridhari.preachingassistant.model.Response.class);

    public final DateTimePath<java.util.Date> timeStamp = createDateTime("timeStamp", java.util.Date.class);

    public QDevoteeHistory(String variable) {
        this(DevoteeHistory.class, forVariable(variable), INITS);
    }

    public QDevoteeHistory(Path<? extends DevoteeHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDevoteeHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDevoteeHistory(PathMetadata metadata, PathInits inits) {
        this(DevoteeHistory.class, metadata, inits);
    }

    public QDevoteeHistory(Class<? extends DevoteeHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commentedByDevotee = inits.isInitialized("commentedByDevotee") ? new QDevotee(forProperty("commentedByDevotee"), inits.get("commentedByDevotee")) : null;
        this.ratedDevotee = inits.isInitialized("ratedDevotee") ? new QDevotee(forProperty("ratedDevotee"), inits.get("ratedDevotee")) : null;
    }

}

