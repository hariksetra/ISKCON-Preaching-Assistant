package com.giridhari.preachingassistant.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "followup_volunteers", catalog = "preaching_assistant", 
	uniqueConstraints={
	    @UniqueConstraint(columnNames = {"program", "devotee"})
	})
public class FollowUpVolunteer {

	private Long id;
	private Program program;
	private Devotee devotee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false, targetEntity = Program.class)
	@JsonManagedReference
	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false, targetEntity = Devotee.class)
	@JsonManagedReference
	public Devotee getDevotee() {
		return devotee;
	}
	
	public void setDevotee(Devotee devotee) {
		this.devotee = devotee;
	}
	
}
