package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollowUpAssignment is a Querydsl query type for FollowUpAssignment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFollowUpAssignment extends EntityPathBase<FollowUpAssignment> {

    private static final long serialVersionUID = -1061986145L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollowUpAssignment followUpAssignment = new QFollowUpAssignment("followUpAssignment");

    public final QDevotee attendee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProgram program;

    public final QDevotee volunteer;

    public QFollowUpAssignment(String variable) {
        this(FollowUpAssignment.class, forVariable(variable), INITS);
    }

    public QFollowUpAssignment(Path<? extends FollowUpAssignment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollowUpAssignment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollowUpAssignment(PathMetadata metadata, PathInits inits) {
        this(FollowUpAssignment.class, metadata, inits);
    }

    public QFollowUpAssignment(Class<? extends FollowUpAssignment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new QDevotee(forProperty("attendee"), inits.get("attendee")) : null;
        this.program = inits.isInitialized("program") ? new QProgram(forProperty("program"), inits.get("program")) : null;
        this.volunteer = inits.isInitialized("volunteer") ? new QDevotee(forProperty("volunteer"), inits.get("volunteer")) : null;
    }

}

