package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollowUp is a Querydsl query type for FollowUp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFollowUp extends EntityPathBase<FollowUp> {

    private static final long serialVersionUID = -485223374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollowUp followUp = new QFollowUp("followUp");

    public final QDevotee attendee;

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProgram program;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final EnumPath<com.giridhari.preachingassistant.model.Response> response = createEnum("response", com.giridhari.preachingassistant.model.Response.class);

    public final NumberPath<Integer> taskStatus = createNumber("taskStatus", Integer.class);

    public final DatePath<java.util.Date> timestamp = createDate("timestamp", java.util.Date.class);

    public final QDevotee volunteer;

    public QFollowUp(String variable) {
        this(FollowUp.class, forVariable(variable), INITS);
    }

    public QFollowUp(Path<? extends FollowUp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollowUp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollowUp(PathMetadata metadata, PathInits inits) {
        this(FollowUp.class, metadata, inits);
    }

    public QFollowUp(Class<? extends FollowUp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new QDevotee(forProperty("attendee"), inits.get("attendee")) : null;
        this.program = inits.isInitialized("program") ? new QProgram(forProperty("program"), inits.get("program")) : null;
        this.volunteer = inits.isInitialized("volunteer") ? new QDevotee(forProperty("volunteer"), inits.get("volunteer")) : null;
    }

}

