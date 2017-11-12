package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImportantDate is a Querydsl query type for ImportantDate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QImportantDate extends EntityPathBase<ImportantDate> {

    private static final long serialVersionUID = 1330481322L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImportantDate importantDate = new QImportantDate("importantDate");

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final QDevotee devotee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath significance = createString("significance");

    public QImportantDate(String variable) {
        this(ImportantDate.class, forVariable(variable), INITS);
    }

    public QImportantDate(Path<? extends ImportantDate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImportantDate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImportantDate(PathMetadata metadata, PathInits inits) {
        this(ImportantDate.class, metadata, inits);
    }

    public QImportantDate(Class<? extends ImportantDate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.devotee = inits.isInitialized("devotee") ? new QDevotee(forProperty("devotee"), inits.get("devotee")) : null;
    }

}

