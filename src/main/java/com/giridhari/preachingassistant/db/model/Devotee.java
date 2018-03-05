package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giridhari.preachingassistant.model.CountryCode;
import com.giridhari.preachingassistant.model.Gender;
import com.giridhari.preachingassistant.model.IncomeScale;
import com.giridhari.preachingassistant.model.MaritalStatus;
import com.giridhari.preachingassistant.model.SikshaLevel;

@Entity
@Table(name = "devotee", catalog = "preaching_assistant",
		uniqueConstraints=@UniqueConstraint(columnNames={"sms_phone"}))
public class Devotee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8385723388391634838L;
	
	private Long id;
	private String legalName;
	private String initiatedName;
	private Date dob;
	private Gender gender;
	private MaritalStatus maritalStatus;
	private String familyInfo;
	private String education;
	private String occupation;
	private String organization;
	private String designation;
	private IncomeScale incomeScale;
	private String smsPhone; //TODO: Create a separate table for phone numbers, which contains number, type of number eg:whatsapp or work, status eg: working or not working
	private Date introDate;
	private String preferredLanguage;
	private String description;
	private String area;
	private String address;
	private CountryCode countryCode;
	private String zipPostalCode;
	private String email;
	private String capturedFor;
	private String booksRead;
	private Integer monthlyContribution;
	private SikshaLevel sikshaLevel; //TODO: create an enum for siksha level
	private Yatra yatra;
	private UserAccount userAccount;
	
	private Set<DevoteeHistory> ratedDevoteeHistory;
	private Set<DevoteeHistory> commentedByDevoteeHistory;
	
	private Set<Yatra> yatras;
	
	private Set<Program> programs;
	private Set<ProgramAssignment> attendingPrograms;
	private Set<ProgramAttendance> myAttendanceRecords;
	
	private Set<FollowUpVolunteer> followUps;
	private Set<FollowUpAssignment> volunteeringFollowUps;
	private Set<FollowUpAssignment> attendingFollowUps;
	private Set<FollowUp> volunteeredFollowUps;
	private Set<FollowUp> attendedFollowUps;
	
	private Set<ImportantDate> importantDates;
	
	private Set<CaptureContact> capturedBy;
	private Set<CaptureContact> capturedDevotees;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="legal_name", length=50, nullable=false)
	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	@Column(name="initiated_name", length=50, nullable = true)
	public String getInitiatedName() {
		return initiatedName;
	}

	public void setInitiatedName(String initiatedName) {
		this.initiatedName = initiatedName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="dob", columnDefinition="date", nullable = true)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name="gender", nullable = true)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Column(name="marital_status", nullable = true	)
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name="family_info", columnDefinition="text", nullable = true)
	public String getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	@Column(name="education", length=50, nullable = true)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name="occupation", length=50, nullable = true)
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name="organization", length=50, nullable = true)
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name="designation", length=50, nullable = true)
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name="income_scale", nullable = true)
	public IncomeScale getIncomeScale() {
		return incomeScale;
	}

	public void setIncomeScale(IncomeScale incomeScale) {
		this.incomeScale = incomeScale;
	}

	@Column(name="sms_phone", nullable = true)
	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="intro_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ", nullable = false, insertable = false, updatable = false)
	public Date getIntroDate() {
		return introDate;
	}

	public void setIntroDate(Date introDate) {
		this.introDate = introDate;
	}
	
	@Column(name = "preferred_lang", length = 50, nullable = true)
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	
	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	

	@Column(name="area", nullable = true)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name="address", columnDefinition="text", nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="country_code", nullable = true)
	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name="zip_postal_code", nullable = true)
	public String getZipPostalCode() {
		return zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	@Column(name="email", nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="captured_for")
	public String getCapturedFor() {
		return capturedFor;
	}

	public void setCapturedFor(String capturedFor) {
		this.capturedFor = capturedFor;
	}

	@Column(name="books_read", nullable = true)
	public String getBooksRead() {
		return booksRead;
	}

	public void setBooksRead(String booksRead) {
		this.booksRead = booksRead;
	}

	@Column(name="monthly_contribution", nullable = true)
	public Integer getMonthlyContribution() {
		return monthlyContribution;
	}

	public void setMonthlyContribution(Integer monthlyContribution) {
		this.monthlyContribution = monthlyContribution;
	}

	@Column(name="siksha_level", nullable = true)
	public SikshaLevel getSikshaLevel() {
		return sikshaLevel;
	}

	public void setSikshaLevel(SikshaLevel sikshaLevel) {
		this.sikshaLevel = sikshaLevel;
	}
	
	@OneToOne(cascade = CascadeType.MERGE, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id", columnDefinition = "integer")
	@JsonBackReference
	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "yatraAdmin")
	@JsonBackReference
	public Set<Yatra> getYatras() {
		return yatras;
	}

	public void setYatras(Set<Yatra> yatras) {
		this.yatras = yatras;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "mentor")
	@JsonBackReference
	public Set<Program> getPrograms() {
		return programs;
	}
	
	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "program")
	@JsonBackReference
	public Set<ProgramAssignment> getAttendingPrograms() {
		return attendingPrograms;
	}

	public void setAttendingPrograms(Set<ProgramAssignment> attendingPrograms) {
		this.attendingPrograms = attendingPrograms;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "programId")
	@JsonBackReference
	public Set<ProgramAttendance> getMyAttendanceRecords() {
		return myAttendanceRecords;
	}

	public void setMyAttendanceRecords(Set<ProgramAttendance> myAttendanceRecords) {
		this.myAttendanceRecords = myAttendanceRecords;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "volunteer")
	@JsonBackReference
	public Set<FollowUp> getVolunteeredFollowUps() {
		return volunteeredFollowUps;
	}
	
	public void setVolunteeredFollowUps(Set<FollowUp> volunteeredFollowUps) {
		this.volunteeredFollowUps = volunteeredFollowUps;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "attendee")
	@JsonBackReference
	public Set<FollowUp> getAttendedFollowUps() {
		return attendedFollowUps;
	}
	
	public void setAttendedFollowUps(Set<FollowUp> attendedFollowUps) {
		this.attendedFollowUps = attendedFollowUps;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "volunteer")
	@JsonBackReference
	public Set<FollowUpAssignment> getVolunteeringFollowUps() {
		return volunteeringFollowUps;
	}
	
	public void setVolunteeringFollowUps(Set<FollowUpAssignment> volunteeringFollowUps) {
		this.volunteeringFollowUps = volunteeringFollowUps;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "attendee")
	@JsonBackReference
	public Set<FollowUpAssignment> getAttendingFollowUps() {
		return attendingFollowUps;
	}
	
	public void setAttendingFollowUps(Set<FollowUpAssignment> attendingFollowUps) {
		this.attendingFollowUps = attendingFollowUps;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "devotee")
	@JsonBackReference
	public Set<ImportantDate> getImportantDates() {
		return importantDates;
	}
	
	public void setImportantDates(Set<ImportantDate> importantDates) {
		this.importantDates = importantDates;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "ratedDevotee")
	@JsonBackReference
	public Set<DevoteeHistory> getRatedDevoteeHistory() {
		return ratedDevoteeHistory;
	}
	
	public void setRatedDevoteeHistory(Set<DevoteeHistory> ratedDevoteeHistory) {
		this.ratedDevoteeHistory = ratedDevoteeHistory;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "commentedByDevotee")
	@JsonBackReference
	public Set<DevoteeHistory> getCommentedByDevoteeHistory() {
		return commentedByDevoteeHistory;
	}

	public void setCommentedByDevoteeHistory(Set<DevoteeHistory> commentedByDevoteeHistory) {
		this.commentedByDevoteeHistory = commentedByDevoteeHistory;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "devotee")
	@JsonBackReference
	public Set<FollowUpVolunteer> getFollowUps() {
		return followUps;
	}
	
	public void setFollowUps(Set<FollowUpVolunteer> followUps) {
		this.followUps = followUps;
	}

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "yatra_id", columnDefinition = "integer" )
	@JsonManagedReference
	public Yatra getYatra() {
		return yatra;
	}

	public void setYatra(Yatra yatra) {
		this.yatra = yatra;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "capturedBy")
	@JsonBackReference
	public Set<CaptureContact> getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(Set<CaptureContact> capturedBy) {
		this.capturedBy = capturedBy;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "capturedDevotee")
	@JsonBackReference
	public Set<CaptureContact> getCapturedDevotees() {
		return capturedDevotees;
	}

	public void setCapturedDevotees(Set<CaptureContact> capturedDevotees) {
		this.capturedDevotees = capturedDevotees;
	}
	
	
}
