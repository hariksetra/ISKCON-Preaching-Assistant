package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;
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
import javax.persistence.Table;

import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.TargetAudiance;

@Entity
@Table(name = "program", catalog = "preaching_assistant")
public class Program implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073830669851492366L;
	
	private Long id;
	private String name;
	private Devotee mentor;
	private Yatra parentYatra;
	private String description;
	private String address;
	private String mapLocation;
	private ProgramType type;
	private TargetAudiance targetAudiance;
	private String followupDescription;
	
	private Set<ProgramAreaSubscription> areasSubscribed;
	private Set<ProgramAssignment> participants;
	private Set<ProgramAttendance> attendanceRecords;
	private Set<FollowUpAssignment> followUpAssignments;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "mentor_id", columnDefinition = "integer" )
	public Devotee getMentor() {
		return mentor;
	}
	
	public void setMentor(Devotee mentor) {
		this.mentor = mentor;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_yatra_id", columnDefinition = "integer" )
	public Yatra getParentYatra() {
		return parentYatra;
	}

	public void setParentYatra(Yatra parentYatra) {
		this.parentYatra = parentYatra;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "address", nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "map_location", nullable = true)
	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	@Column(name = "type", nullable = true)
	public ProgramType getType() {
		return type;
	}

	public void setType(ProgramType type) {
		this.type = type;
	}

	@Column(name = "target_audiance", nullable = true)
	public TargetAudiance getTargetAudiance() {
		return targetAudiance;
	}

	public void setTargetAudiance(TargetAudiance targetAudiance) {
		this.targetAudiance = targetAudiance;
	}
	
	@Column(name = "followup_description", nullable = true)
	public String getFollowupDescription() {
		return followupDescription;
	}

	public void setFollowupDescription(String followupDescription) {
		this.followupDescription = followupDescription;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="programId")
	public Set<ProgramAreaSubscription> getAreasSubscribed() {
		return areasSubscribed;
	}

	public void setAreasSubscribed(Set<ProgramAreaSubscription> areasSubscribed) {
		this.areasSubscribed = areasSubscribed;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="program")
	public Set<ProgramAssignment> getParticipants() {
		return participants;
	}
	
	public void setParticipants(Set<ProgramAssignment> participants) {
		this.participants = participants;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="programId")
	public Set<ProgramAttendance> getAttendanceRecords() {
		return attendanceRecords;
	}

	public void setAttendanceRecords(Set<ProgramAttendance> attendanceRecords) {
		this.attendanceRecords = attendanceRecords;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="program")
	public Set<FollowUpAssignment> getFollowUpAssignments() {
		return followUpAssignments;
	}
	
	public void setFollowUpAssignments(Set<FollowUpAssignment> followUpAssignments) {
		this.followUpAssignments = followUpAssignments;
	}
}
