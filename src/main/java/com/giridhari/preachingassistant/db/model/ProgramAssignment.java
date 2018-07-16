package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "program_assignment", catalog = "preaching_assistant", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"program_id", "attendee_id"})
	})
public class ProgramAssignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5132338105953635023L;
	
	private Long id;
	private Program program;
	private Devotee attendee;
	private Date dateAdded;
	
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
	@JoinColumn(name = "program_id", columnDefinition = "integer")
	@JsonManagedReference
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "attendee_id", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getAttendee() {
		return attendee;
	}

	public void setAttendee(Devotee attendee) {
		this.attendee = attendee;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_added_time_stamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ", nullable = false, insertable = false, updatable = false)
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
}
