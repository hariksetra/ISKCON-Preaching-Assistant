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

import com.giridhari.preachingassistant.model.Gender;
import com.giridhari.preachingassistant.model.IncomeScale;
import com.giridhari.preachingassistant.model.MaritalStatus;

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
	private Devotee capturedBy;
	private String area; //TODO: create a table for area later
	private String address;
	private String email;
	private String capturedFor;
	private String booksRead; //TODO: create a separate table to maintain the list of books read
	private Integer monthlyContribution;
	private String sikshaLevel; //TODO: create an enum for siksha level
	private UserAccount userAccount;
	private Set<Devotee> capturedDevotees;
	private Set<Program> programs;
	private Set<FollowUp> volunteeredFollowUps;
	private Set<FollowUp> attendedFollowUps;
	private Set<FollowUpAssignment> volunteeringFollowUps;
	private Set<FollowUpAssignment> attendingFollowUps;

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

	@Column(name="gender", nullable = false)
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

	@Column(name="sms_phone", nullable = false)
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
	
	@ManyToOne(optional =true, targetEntity = Devotee.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "captured_by", columnDefinition = "integer", nullable = true)
	public Devotee getCapturedBy() {
		return capturedBy;
	}
	
	public void setCapturedBy(Devotee capturedBy) {
		this.capturedBy = capturedBy;
	}

	@Column(name="area", nullable = false)
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
	public String getSikshaLevel() {
		return sikshaLevel;
	}

	public void setSikshaLevel(String sikshaLevel) {
		this.sikshaLevel = sikshaLevel;
	}
	
	@OneToOne(cascade = CascadeType.MERGE, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id", columnDefinition = "integer")
	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "capturedBy")
	public Set<Devotee> getCapturedDevotees() {
		return capturedDevotees;
	}
	
	public void setCapturedDevotees(Set<Devotee> capturedDevotees) {
		this.capturedDevotees = capturedDevotees;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "mentor")
	public Set<Program> getPrograms() {
		return programs;
	}
	
	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "volunteer")
	public Set<FollowUp> getVolunteeredFollowUps() {
		return volunteeredFollowUps;
	}
	
	public void setVolunteeredFollowUps(Set<FollowUp> volunteeredFollowUps) {
		this.volunteeredFollowUps = volunteeredFollowUps;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "attendee")
	public Set<FollowUp> getAttendedFollowUps() {
		return attendedFollowUps;
	}
	
	public void setAttendedFollowUps(Set<FollowUp> attendedFollowUps) {
		this.attendedFollowUps = attendedFollowUps;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "volunteer")
	public Set<FollowUpAssignment> getVolunteeringFollowUps() {
		return volunteeringFollowUps;
	}
	
	public void setVolunteeringFollowUps(Set<FollowUpAssignment> volunteeringFollowUps) {
		this.volunteeringFollowUps = volunteeringFollowUps;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "attendee")
	public Set<FollowUpAssignment> getAttendingFollowUps() {
		return attendingFollowUps;
	}
	
	public void setAttendingFollowUps(Set<FollowUpAssignment> attendingFollowUps) {
		this.attendingFollowUps = attendingFollowUps;
	}
	
}
