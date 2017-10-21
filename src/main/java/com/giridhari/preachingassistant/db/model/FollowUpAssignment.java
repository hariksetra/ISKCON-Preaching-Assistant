package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "follow_up_assignment", catalog = "preaching_assistant")
public class FollowUpAssignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933820377671618450L;
	
	private Long id;
	private Devotee attendee;
	private Devotee volunteer;
	private Program program;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "attendee_id", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getAttendee() {
		return attendee;
	}

	public void setAttendee(Devotee attendee) {
		this.attendee = attendee;
	}

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "volunteer_id", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Devotee volunteer) {
		this.volunteer = volunteer;
	}

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "program_id", columnDefinition = "integer")
	@JsonManagedReference
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
}
