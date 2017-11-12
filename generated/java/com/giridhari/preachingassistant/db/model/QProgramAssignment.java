package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgramAssignment is a Querydsl query type for ProgramAssignment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProgramAssignment extends EntityPathBase<ProgramAssignment> {

    private static final long serialVersionUID = 566336811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProgramAssignment programAssignment = new QProgramAssignment("programAssignment");

    public final QDevotee attendee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProgram program;

    public QProgramAssignment(String variable) {
        this(ProgramAssignment.class, forVariable(variable), INITS);
    }

    public QProgramAssignment(Path<? extends ProgramAssignment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProgramAssignment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProgramAssignment(PathMetadata metadata, PathInits inits) {
        this(ProgramAssignment.class, metadata, inits);
    }

    public QProgramAssignment(Class<? extends ProgramAssignment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new QDevotee(forProperty("attendee"), inits.get("attendee")) : null;
        this.program = inits.isInitialized("program") ? new QProgram(forProperty("program"), inits.get("program")) : null;
    }

}

