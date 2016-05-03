package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "followup", catalog = "preaching_assistant")
public class Followup implements Serializable {

	private Long id;
	private Program program;
	private Devotee volunteerId;
	private Devotee attendeeId;
	private FollowupStatus status;
	private Integer rating;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public Devotee getVolunteerId() {
		return volunteerId;
	}
	public void setVolunteerId(Devotee volunteerId) {
		this.volunteerId = volunteerId;
	}
	public Devotee getAttendeeId() {
		return attendeeId;
	}
	public void setAttendeeId(Devotee attendeeId) {
		this.attendeeId = attendeeId;
	}
	public FollowupStatus getStatus() {
		return status;
	}
	public void setStatus(FollowupStatus status) {
		this.status = status;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}
