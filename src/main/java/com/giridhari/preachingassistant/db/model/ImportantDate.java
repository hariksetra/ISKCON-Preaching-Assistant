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

@Entity
@Table(name = "important_date", catalog = "preaching_assistant")
public class ImportantDate {

	private Long id;
	private Devotee devotee;
	private String significance;
	private Date date;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(optional = false, targetEntity = Devotee.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference
	public Devotee getDevotee() {
		return devotee;
	}
	
	public void setDevotee(Devotee devotee) {
		this.devotee = devotee;
	}
	
	@Column(name = "significance", nullable = false)
	public String getSignificance() {
		return significance;
	}
	
	public void setSignificance(String significance) {
		this.significance = significance;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="date", columnDefinition="date", nullable = true)
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
