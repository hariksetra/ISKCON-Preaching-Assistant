package com.giridhari.preachingassistant.db.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "devotee_history", schema= "preaching_assistant")
public class DevoteeHistory {

	private Long id;
	private FollowUpVolunteer followUpVolunteer;
	private Devotee devotee;
	private Integer rating;
	private Response response;
	private String comment;
	private Date timeStamp;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(optional = false, targetEntity = FollowUpVolunteer.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public FollowUpVolunteer getFollowUpVolunteer() {
		return followUpVolunteer;
	}

	public void setFollowUpVolunteer(FollowUpVolunteer followUpVolunteer) {
		this.followUpVolunteer = followUpVolunteer;
	}

	@ManyToOne(optional = false, targetEntity = Devotee.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public Devotee getDevotee() {
		return devotee;
	}

	public void setDevotee(Devotee devotee) {
		this.devotee = devotee;
	}

	@Column(name = "rating", columnDefinition = "integer", nullable = true)
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "response", nullable = true)
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

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="time_stamp", columnDefinition="date", nullable = true)
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
