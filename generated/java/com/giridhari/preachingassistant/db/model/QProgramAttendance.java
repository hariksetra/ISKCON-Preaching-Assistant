package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgramAttendance is a Querydsl query type for ProgramAttendance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProgramAttendance extends EntityPathBase<ProgramAttendance> {

    private static final long serialVersionUID = 1437464903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProgramAttendance programAttendance = new QProgramAttendance("programAttendance");

    public final DatePath<java.util.Date> attendanceDate = createDate("attendanceDate", java.util.Date.class);

    public final QDevotee devoteeId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProgram programId;

    public final StringPath topic = createString("topic");

    public QProgramAttendance(String variable) {
        this(ProgramAttendance.class, forVariable(variable), INITS);
    }

    public QProgramAttendance(Path<? extends ProgramAttendance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProgramAttendance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProgramAttendance(PathMetadata metadata, PathInits inits) {
        this(ProgramAttendance.class, metadata, inits);
    }

    public QProgramAttendance(Class<? extends ProgramAttendance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.devoteeId = inits.isInitialized("devoteeId") ? new QDevotee(forProperty("devoteeId"), inits.get("devoteeId")) : null;
        this.programId = inits.isInitialized("programId") ? new QProgram(forProperty("programId"), inits.get("programId")) : null;
    }

}

