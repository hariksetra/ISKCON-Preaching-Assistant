package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgram is a Querydsl query type for Program
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProgram extends EntityPathBase<Program> {

    private static final long serialVersionUID = 912150462L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProgram program = new QProgram("program");

    public final StringPath address = createString("address");

    public final SetPath<ProgramAreaSubscription, QProgramAreaSubscription> areasSubscribed = this.<ProgramAreaSubscription, QProgramAreaSubscription>createSet("areasSubscribed", ProgramAreaSubscription.class, QProgramAreaSubscription.class, PathInits.DIRECT2);

    public final SetPath<ProgramAttendance, QProgramAttendance> attendanceRecords = this.<ProgramAttendance, QProgramAttendance>createSet("attendanceRecords", ProgramAttendance.class, QProgramAttendance.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final SetPath<FollowUpAssignment, QFollowUpAssignment> followUpAssignments = this.<FollowUpAssignment, QFollowUpAssignment>createSet("followUpAssignments", FollowUpAssignment.class, QFollowUpAssignment.class, PathInits.DIRECT2);

    public final StringPath followupDescription = createString("followupDescription");

    public final SetPath<FollowUp, QFollowUp> followUps = this.<FollowUp, QFollowUp>createSet("followUps", FollowUp.class, QFollowUp.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mapLocation = createString("mapLocation");

    public final QDevotee mentor;

    public final StringPath name = createString("name");

    public final QYatra parentYatra;

    public final SetPath<ProgramAssignment, QProgramAssignment> participants = this.<ProgramAssignment, QProgramAssignment>createSet("participants", ProgramAssignment.class, QProgramAssignment.class, PathInits.DIRECT2);

    public final EnumPath<com.giridhari.preachingassistant.model.TargetAudience> targetAudience = createEnum("targetAudience", com.giridhari.preachingassistant.model.TargetAudience.class);

    public final EnumPath<com.giridhari.preachingassistant.model.ProgramType> type = createEnum("type", com.giridhari.preachingassistant.model.ProgramType.class);

    public QProgram(String variable) {
        this(Program.class, forVariable(variable), INITS);
    }

    public QProgram(Path<? extends Program> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProgram(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProgram(PathMetadata metadata, PathInits inits) {
        this(Program.class, metadata, inits);
    }

    public QProgram(Class<? extends Program> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mentor = inits.isInitialized("mentor") ? new QDevotee(forProperty("mentor"), inits.get("mentor")) : null;
        this.parentYatra = inits.isInitialized("parentYatra") ? new QYatra(forProperty("parentYatra"), inits.get("parentYatra")) : null;
    }

}

