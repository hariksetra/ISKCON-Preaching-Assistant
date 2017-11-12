package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollowUpVolunteer is a Querydsl query type for FollowUpVolunteer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFollowUpVolunteer extends EntityPathBase<FollowUpVolunteer> {

    private static final long serialVersionUID = -1085890696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollowUpVolunteer followUpVolunteer = new QFollowUpVolunteer("followUpVolunteer");

    public final QDevotee devotee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProgram program;

    public QFollowUpVolunteer(String variable) {
        this(FollowUpVolunteer.class, forVariable(variable), INITS);
    }

    public QFollowUpVolunteer(Path<? extends FollowUpVolunteer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollowUpVolunteer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollowUpVolunteer(PathMetadata metadata, PathInits inits) {
        this(FollowUpVolunteer.class, metadata, inits);
    }

    public QFollowUpVolunteer(Class<? extends FollowUpVolunteer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.devotee = inits.isInitialized("devotee") ? new QDevotee(forProperty("devotee"), inits.get("devotee")) : null;
        this.program = inits.isInitialized("program") ? new QProgram(forProperty("program"), inits.get("program")) : null;
    }

}

