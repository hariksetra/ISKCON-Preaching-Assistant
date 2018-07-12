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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.TargetAudience;

@Entity
@Table(name = "program", catalog = "preaching_assistant",
uniqueConstraints=@UniqueConstraint(columnNames={"name", "parent_yatra_id"}))
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
	private TargetAudience targetAudience;
	private String followupDescription;
	
	private Set<ProgramAreaSubscription> areasSubscribed;
	private Set<ProgramAssignment> participants;
	private Set<FollowUpAssignment> followUpAssignments;
	private Set<FollowUp> followUps;
	private Set<ProgramSession> sessions;
	
	
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
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "mentor_id", columnDefinition = "integer" )
	@JsonManagedReference
	public Devotee getMentor() {
		return mentor;
	}
	
	public void setMentor(Devotee mentor) {
		this.mentor = mentor;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_yatra_id", columnDefinition = "integer" )
	@JsonManagedReference
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

	@Column(name = "target_audience", nullable = true)
	public TargetAudience getTargetAudience() {
		return targetAudience;
	}

	public void setTargetAudience(TargetAudience targetAudience) {
		this.targetAudience = targetAudience;
	}
	
	@Column(name = "followup_description", nullable = true)
	public String getFollowupDescription() {
		return followupDescription;
	}

	public void setFollowupDescription(String followupDescription) {
		this.followupDescription = followupDescription;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="programId")
	@JsonBackReference
	public Set<ProgramAreaSubscription> getAreasSubscribed() {
		return areasSubscribed;
	}

	public void setAreasSubscribed(Set<ProgramAreaSubscription> areasSubscribed) {
		this.areasSubscribed = areasSubscribed;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="program")
	@JsonBackReference
	public Set<ProgramAssignment> getParticipants() {
		return participants;
	}
	
	public void setParticipants(Set<ProgramAssignment> participants) {
		this.participants = participants;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="program")
	@JsonBackReference
	public Set<FollowUpAssignment> getFollowUpAssignments() {
		return followUpAssignments;
	}
	
	public void setFollowUpAssignments(Set<FollowUpAssignment> followUpAssignments) {
		this.followUpAssignments = followUpAssignments;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="program")
	@JsonBackReference
	public Set<FollowUp> getFollowUps() {
		return followUps;
	}

	public void setFollowUps(Set<FollowUp> followUps) {
		this.followUps = followUps;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="program")
	@JsonBackReference
	public Set<ProgramSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<ProgramSession> sessions) {
		this.sessions = sessions;
	}
	
	
}
