package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAccount is a Querydsl query type for UserAccount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAccount extends EntityPathBase<UserAccount> {

    private static final long serialVersionUID = -1495961572L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAccount userAccount = new QUserAccount("userAccount");

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final QDevotee profile;

    public final EnumPath<Type> type = createEnum("type", Type.class);

    public final StringPath username = createString("username");

    public QUserAccount(String variable) {
        this(UserAccount.class, forVariable(variable), INITS);
    }

    public QUserAccount(Path<? extends UserAccount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAccount(PathMetadata metadata, PathInits inits) {
        this(UserAccount.class, metadata, inits);
    }

    public QUserAccount(Class<? extends UserAccount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QDevotee(forProperty("profile"), inits.get("profile")) : null;
    }

}

