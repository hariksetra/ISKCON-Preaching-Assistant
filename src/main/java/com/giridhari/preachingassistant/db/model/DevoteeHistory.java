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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giridhari.preachingassistant.model.Response;

@Entity
@Table(name = "devotee_history", catalog= "preaching_assistant")
public class DevoteeHistory {

	private Long id;
	private Devotee commentedByDevotee;
	private Devotee ratedDevotee;
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
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference
	public Devotee getCommentedByDevotee() {
		return commentedByDevotee;
	}

	public void setCommentedByDevotee(Devotee commentedByDevotee) {
		this.commentedByDevotee = commentedByDevotee;
	}

	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference
	public Devotee getRatedDevotee() {
		return ratedDevotee;
	}

	public void setRatedDevotee(Devotee ratedDevotee) {
		this.ratedDevotee = ratedDevotee;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="time_stamp", columnDefinition="date ", nullable = false)
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
