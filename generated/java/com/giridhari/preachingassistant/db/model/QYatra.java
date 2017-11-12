package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QYatra is a Querydsl query type for Yatra
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QYatra extends EntityPathBase<Yatra> {

    private static final long serialVersionUID = 911551829L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QYatra yatra = new QYatra("yatra");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Program, QProgram> programsInYatra = this.<Program, QProgram>createSet("programsInYatra", Program.class, QProgram.class, PathInits.DIRECT2);

    public final StringPath yatraAddress = createString("yatraAddress");

    public final QDevotee yatraAdmin;

    public final StringPath yatraName = createString("yatraName");

    public final EnumPath<com.giridhari.preachingassistant.model.YatraType> yatraType = createEnum("yatraType", com.giridhari.preachingassistant.model.YatraType.class);

    public QYatra(String variable) {
        this(Yatra.class, forVariable(variable), INITS);
    }

    public QYatra(Path<? extends Yatra> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QYatra(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QYatra(PathMetadata metadata, PathInits inits) {
        this(Yatra.class, metadata, inits);
    }

    public QYatra(Class<? extends Yatra> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.yatraAdmin = inits.isInitialized("yatraAdmin") ? new QDevotee(forProperty("yatraAdmin"), inits.get("yatraAdmin")) : null;
    }

}

