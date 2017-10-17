package com.giridhari.preachingassistant.db.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "followup_volunteers", catalog = "preaching_assistant")
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
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false, targetEntity = Program.class)
	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false, targetEntity = Devotee.class)
	public Devotee getDevotee() {
		return devotee;
	}
	
	public void setDevotee(Devotee devotee) {
		this.devotee = devotee;
	}
	
}
