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
@Table(name = "program", catalog = "preaching_assistant")
public class ProgramAttendance {

	private Long id;
	private Date attendanceDate;
	private Program programId;
	private Devotee devoteeId;
	private String topic;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="attendance_date", columnDefinition="date", nullable = true)
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "program_id", columnDefinition = "integer")
	@JsonManagedReference
	public Program getProgramId() {
		return programId;
	}
	
	public void setProgramId(Program programId) {
		this.programId = programId;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "devotee_id", columnDefinition = "integer")
	@JsonManagedReference
	public Devotee getDevoteeId() {
		return devoteeId;
	}
	
	public void setDevoteeId(Devotee devoteeId) {
		this.devoteeId = devoteeId;
	}
	
	@Column(name="legal_name", nullable=true)
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
