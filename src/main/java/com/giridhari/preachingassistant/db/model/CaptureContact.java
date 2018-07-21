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

@Entity
@Table(name = "capture_contact", catalog = "preaching_assistant")
public class CaptureContact {
	private Long id;
	private Devotee capturedBy;
	private Devotee capturedDevotee;
	private String intoducedrAt;
	private Date timestamp;
	
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
	@JoinColumn(name = "captured_by", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getCapturedBy() {
		return capturedBy;
	}
	
	public void setCapturedBy(Devotee capturedBy) {
		this.capturedBy = capturedBy;
	}
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "captured_devotee", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getCapturedDevotee() {
		return capturedDevotee;
	}
	
	public void setCapturedDevotee(Devotee capturedDevotee) {
		this.capturedDevotee = capturedDevotee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="date", columnDefinition="date", nullable = true)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "introduced_at", nullable = true)
	public String getIntoducedrAt() {
		return intoducedrAt;
	}

	public void setIntoducedrAt(String intoducedrAt) {
		this.intoducedrAt = intoducedrAt;
	}
}
