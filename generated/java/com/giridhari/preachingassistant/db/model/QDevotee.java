package com.giridhari.preachingassistant.db.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDevotee is a Querydsl query type for Devotee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevotee extends EntityPathBase<Devotee> {

    private static final long serialVersionUID = -1513433068L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDevotee devotee = new QDevotee("devotee");

    public final StringPath address = createString("address");

    public final StringPath area = createString("area");

    public final SetPath<FollowUp, QFollowUp> attendedFollowUps = this.<FollowUp, QFollowUp>createSet("attendedFollowUps", FollowUp.class, QFollowUp.class, PathInits.DIRECT2);

    public final SetPath<FollowUpAssignment, QFollowUpAssignment> attendingFollowUps = this.<FollowUpAssignment, QFollowUpAssignment>createSet("attendingFollowUps", FollowUpAssignment.class, QFollowUpAssignment.class, PathInits.DIRECT2);

    public final SetPath<ProgramAssignment, QProgramAssignment> attendingPrograms = this.<ProgramAssignment, QProgramAssignment>createSet("attendingPrograms", ProgramAssignment.class, QProgramAssignment.class, PathInits.DIRECT2);

    public final StringPath booksRead = createString("booksRead");

    public final QDevotee capturedBy;

    public final SetPath<Devotee, QDevotee> capturedDevotees = this.<Devotee, QDevotee>createSet("capturedDevotees", Devotee.class, QDevotee.class, PathInits.DIRECT2);

    public final StringPath capturedFor = createString("capturedFor");

    public final SetPath<DevoteeHistory, QDevoteeHistory> commentedByDevoteeHistory = this.<DevoteeHistory, QDevoteeHistory>createSet("commentedByDevoteeHistory", DevoteeHistory.class, QDevoteeHistory.class, PathInits.DIRECT2);

    public final EnumPath<com.giridhari.preachingassistant.model.CountryCode> countryCode = createEnum("countryCode", com.giridhari.preachingassistant.model.CountryCode.class);

    public final StringPath description = createString("description");

    public final StringPath designation = createString("designation");

    public final DatePath<java.util.Date> dob = createDate("dob", java.util.Date.class);

    public final StringPath education = createString("education");

    public final StringPath email = createString("email");

    public final StringPath familyInfo = createString("familyInfo");

    public final SetPath<FollowUpVolunteer, QFollowUpVolunteer> followUps = this.<FollowUpVolunteer, QFollowUpVolunteer>createSet("followUps", FollowUpVolunteer.class, QFollowUpVolunteer.class, PathInits.DIRECT2);

    public final EnumPath<com.giridhari.preachingassistant.model.Gender> gender = createEnum("gender", com.giridhari.preachingassistant.model.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<ImportantDate, QImportantDate> importantDates = this.<ImportantDate, QImportantDate>createSet("importantDates", ImportantDate.class, QImportantDate.class, PathInits.DIRECT2);

    public final EnumPath<com.giridhari.preachingassistant.model.IncomeScale> incomeScale = createEnum("incomeScale", com.giridhari.preachingassistant.model.IncomeScale.class);

    public final StringPath initiatedName = createString("initiatedName");

    public final DateTimePath<java.util.Date> introDate = createDateTime("introDate", java.util.Date.class);

    public final StringPath legalName = createString("legalName");

    public final EnumPath<com.giridhari.preachingassistant.model.MaritalStatus> maritalStatus = createEnum("maritalStatus", com.giridhari.preachingassistant.model.MaritalStatus.class);

    public final NumberPath<Integer> monthlyContribution = createNumber("monthlyContribution", Integer.class);

    public final SetPath<ProgramAttendance, QProgramAttendance> myAttendanceRecords = this.<ProgramAttendance, QProgramAttendance>createSet("myAttendanceRecords", ProgramAttendance.class, QProgramAttendance.class, PathInits.DIRECT2);

    public final StringPath occupation = createString("occupation");

    public final StringPath organization = createString("organization");

    public final StringPath preferredLanguage = createString("preferredLanguage");

    public final SetPath<Program, QProgram> programs = this.<Program, QProgram>createSet("programs", Program.class, QProgram.class, PathInits.DIRECT2);

    public final SetPath<DevoteeHistory, QDevoteeHistory> ratedDevoteeHistory = this.<DevoteeHistory, QDevoteeHistory>createSet("ratedDevoteeHistory", DevoteeHistory.class, QDevoteeHistory.class, PathInits.DIRECT2);

    public final EnumPath<com.giridhari.preachingassistant.model.SikshaLevel> sikshaLevel = createEnum("sikshaLevel", com.giridhari.preachingassistant.model.SikshaLevel.class);

    public final StringPath smsPhone = createString("smsPhone");

    public final QUserAccount userAccount;

    public final SetPath<FollowUp, QFollowUp> volunteeredFollowUps = this.<FollowUp, QFollowUp>createSet("volunteeredFollowUps", FollowUp.class, QFollowUp.class, PathInits.DIRECT2);

    public final SetPath<FollowUpAssignment, QFollowUpAssignment> volunteeringFollowUps = this.<FollowUpAssignment, QFollowUpAssignment>createSet("volunteeringFollowUps", FollowUpAssignment.class, QFollowUpAssignment.class, PathInits.DIRECT2);

    public final SetPath<Yatra, QYatra> yatras = this.<Yatra, QYatra>createSet("yatras", Yatra.class, QYatra.class, PathInits.DIRECT2);

    public final StringPath zipPostalCode = createString("zipPostalCode");

    public QDevotee(String variable) {
        this(Devotee.class, forVariable(variable), INITS);
    }

    public QDevotee(Path<? extends Devotee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDevotee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDevotee(PathMetadata metadata, PathInits inits) {
        this(Devotee.class, metadata, inits);
    }

    public QDevotee(Class<? extends Devotee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.capturedBy = inits.isInitialized("capturedBy") ? new QDevotee(forProperty("capturedBy"), inits.get("capturedBy")) : null;
        this.userAccount = inits.isInitialized("userAccount") ? new QUserAccount(forProperty("userAccount"), inits.get("userAccount")) : null;
    }

}

