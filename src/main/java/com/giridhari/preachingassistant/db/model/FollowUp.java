package com.giridhari.preachingassistant.db.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giridhari.preachingassistant.model.Response;

@Entity
@Table(name = "follow_up", catalog = "preaching_assistant")
public class FollowUp {
	
	private Long id;
	private Devotee volunteer;
	private Devotee attendee;
	private Program program;
	private Response response;
	private String comment;
	private Integer rating;
	private Date timestamp;
	private Integer taskStatus; 
	
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
	@JoinColumn(name = "volunteer_id", columnDefinition = "integer", nullable = false)
	@JsonManagedReference
	public Devotee getVolunteer() {
		return volunteer;
	}
	
	public void setVolunteer(Devotee volunteer) {
		this.volunteer = volunteer;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "attendee_id", columnDefinition = "integer", nullable = false)
	@JsonManagedReference
	public Devotee getAttendee() {
		return attendee;
	}
	
	public void setAttendee(Devotee attendee) {
		this.attendee = attendee;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "program_id", columnDefinition = "integer", nullable = false)
	@JsonManagedReference
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	@Column(name = "response", nullable = false)
	public Response getResponse() {
		return response;
	}
	
	public void setResponse(Response response) {
		this.response = response;
	}
	
	@Column(name = "comment", nullable = true)
	public String getComment() {
		return comment;
	}
	
	public void setComment(String response) {
		this.comment = response;
	}
	
	@Column(name = "rating", columnDefinition = "integer", nullable = false)
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="followup_date", columnDefinition="date", nullable = false)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "task_status", nullable = true)
	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
}
