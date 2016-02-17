package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DevoteeInfo")
public class Devotee implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DIDevoteeID")
	private Long id;
	
	@Column(name="DILegalName", length=50, nullable=false)
	private String legalName;
	
	@Column(name="DIInitiatedName", length=50)
	private String initiatedName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DIDOB", columnDefinition="date")
	private Date dob;
	
	@Column(name="DIGender")
	private Gender gender;
	
	@Column(name="DIMaritalStatus")
	private MaritalStatus maritalStatus;
	
	@Column(name="DIFamilyInfo", columnDefinition="text")
	private String familyInfo;
	
	@Column(name="DIEducation", length=50)
	private String education;
	
	@Column(name="DIOccupation", length=50)
	private String occupation;
	
	@Column(name="DIOrganization", length=50)
	private String organization;
	
	@Column(name="DIDesignation", length=50)
	private String designation;
	
	@Column(name="DIIncomeScale")
	private String incomeScale; //TODO: Convert to enum later
	
	@Column(name="DISmsPhone")
	private String smsPhone; //TODO: Create a seperate table for phone numbers, whcih contains number, type of number eg:whatsapp or work, status eg: working or not working
	
	@Temporal(TemporalType.DATE)
	@Column(name="DIIntroDate", columnDefinition="date")
	private Date introDate;
	
	@Column(name="DIArea")
	private String area; //TODO: create a table for area later
	
	@Column(name="DIAddress", columnDefinition="text")
	private String address;
	
	@Column(name="DIEmail")
	private String email;
	
	@Column(name="DICapturedFor")
	private String capturedFor;
	
	@ManyToOne
	private Devotee capturedBy;
	
	@OneToMany(mappedBy="capturedBy")
	private Set<Devotee> capturedDevotees;
	
	@Column(name="DIBooksRead")
	private String booksRead; //TODO: create a seperate table to maintain the list of books read
	
	@Column(name="DIMonthlyContribution")
	private int monthlyContribution;
	
	@Column(name="DISikshaLevel")
	private String sikshaLevel; //TODO: create an enum for siksha level
	
	//TODO: move rating to devotee history
	//TODO: maintain seperate login table
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DIUpdate_ts", columnDefinition="timestamp")
	private Date updateTimeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getInitiatedName() {
		return initiatedName;
	}

	public void setInitiatedName(String initiatedName) {
		this.initiatedName = initiatedName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getIncomeScale() {
		return incomeScale;
	}

	public void setIncomeScale(String incomeScale) {
		this.incomeScale = incomeScale;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public Date getIntroDate() {
		return introDate;
	}

	public void setIntroDate(Date introDate) {
		this.introDate = introDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCapturedFor() {
		return capturedFor;
	}

	public void setCapturedFor(String capturedFor) {
		this.capturedFor = capturedFor;
	}

	public Devotee getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(Devotee capturedBy) {
		this.capturedBy = capturedBy;
	}

	public Set<Devotee> getCapturedDevotees() {
		return capturedDevotees;
	}

	public void setCapturedDevotees(Set<Devotee> capturedDevotees) {
		this.capturedDevotees = capturedDevotees;
	}

	public String getBooksRead() {
		return booksRead;
	}

	public void setBooksRead(String booksRead) {
		this.booksRead = booksRead;
	}

	public int getMonthlyContribution() {
		return monthlyContribution;
	}

	public void setMonthlyContribution(int monthlyContribution) {
		this.monthlyContribution = monthlyContribution;
	}

	public String getSikshaLevel() {
		return sikshaLevel;
	}

	public void setSikshaLevel(String sikshaLevel) {
		this.sikshaLevel = sikshaLevel;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}
}
