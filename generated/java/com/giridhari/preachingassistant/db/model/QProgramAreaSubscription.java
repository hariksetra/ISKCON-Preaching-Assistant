package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgramAreaSubscription is a Querydsl query type for ProgramAreaSubscription
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProgramAreaSubscription extends EntityPathBase<ProgramAreaSubscription> {

    private static final long serialVersionUID = -403084760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProgramAreaSubscription programAreaSubscription = new QProgramAreaSubscription("programAreaSubscription");

    public final EnumPath<com.giridhari.preachingassistant.model.CountryCode> countryCode = createEnum("countryCode", com.giridhari.preachingassistant.model.CountryCode.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProgram programId;

    public final StringPath zipPostalCode = createString("zipPostalCode");

    public QProgramAreaSubscription(String variable) {
        this(ProgramAreaSubscription.class, forVariable(variable), INITS);
    }

    public QProgramAreaSubscription(Path<? extends ProgramAreaSubscription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProgramAreaSubscription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProgramAreaSubscription(PathMetadata metadata, PathInits inits) {
        this(ProgramAreaSubscription.class, metadata, inits);
    }

    public QProgramAreaSubscription(Class<? extends ProgramAreaSubscription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.programId = inits.isInitialized("programId") ? new QProgram(forProperty("programId"), inits.get("programId")) : null;
    }

}

