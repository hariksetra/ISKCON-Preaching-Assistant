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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "program_attendance", catalog = "preaching_assistant",
	uniqueConstraints={
	    @UniqueConstraint(columnNames = {"session_id", "devotee_id"})
	})
public class ProgramAttendance {

	private Long id;
	private ProgramSession session;
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
	
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "session_id", columnDefinition = "integer")
	@JsonManagedReference
	public ProgramSession getSession() {
		return session;
	}
	
	public void setSession(ProgramSession session) {
		this.session = session;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "devotee_id", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getDevotee() {
		return devotee;
	}
	
	public void setDevotee(Devotee devotee) {
		this.devotee = devotee;
	}
}
