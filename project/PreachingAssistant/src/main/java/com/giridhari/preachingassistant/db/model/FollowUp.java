package com.giridhari.preachingassistant.db.model;

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

@Entity
@Table(name = "follow_up", catalog = "preaching_assistant")
public class FollowUp {
	
	private Long id;
	private Devotee volunteer;
	private Devotee attendee;
	private FollowupStatus status;
	private String response;
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
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "volunteer_id", columnDefinition = "integer", nullable = false)
	public Devotee getVolunteer() {
		return volunteer;
	}
	
	public void setVolunteer(Devotee volunteer) {
		this.volunteer = volunteer;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "attendee_id", columnDefinition = "integer", nullable = false)
	public Devotee getAttendee() {
		return attendee;
	}
	
	public void setAttendee(Devotee attendee) {
		this.attendee = attendee;
	}
	
	@Column(name = "status", nullable = false)
	public FollowupStatus getStatus() {
		return status;
	}
	
	public void setStatus(FollowupStatus status) {
		this.status = status;
	}
	
	@Column(name = "response", nullable = true)
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Column(name = "rating", columnDefinition = "integer", nullable = false)
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
