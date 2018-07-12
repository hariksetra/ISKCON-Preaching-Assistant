package com.giridhari.preachingassistant.db.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "program_session", catalog = "preaching_assistant",
	uniqueConstraints={
	    @UniqueConstraint(columnNames = {"program", "session_date"})
	})
public class ProgramSession {

	private Long id;
	private Program program;
	private Date sessionDate;
	private String topic;
	
	private Program programForCurrentFollowup;
	private Set<ProgramAttendance> attendanceForSession;
	private Set<FollowUp> followupsForSession;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(optional = false, targetEntity = Program.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="session_date", columnDefinition="date", nullable = false)
	public Date getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	
	@Column(name = "topic", nullable = true)
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="session")
	@JsonBackReference
	public Set<ProgramAttendance> getAttendanceForSession() {
		return attendanceForSession;
	}
	public void setAttendanceForSession(Set<ProgramAttendance> attendanceForSession) {
		this.attendanceForSession = attendanceForSession;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="followupForSession")
	@JsonBackReference
	public Set<FollowUp> getFollowupsForSession() {
		return followupsForSession;
	}
	public void setFollowupsForSession(Set<FollowUp> followupsForSession) {
		this.followupsForSession = followupsForSession;
	}
	
	@OneToOne(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false, mappedBy = "currentFollowupSession")
	public Program getProgramForCurrentFollowup() {
		return programForCurrentFollowup;
	}
	public void setProgramForCurrentFollowup(Program programForCurrentFollowup) {
		this.programForCurrentFollowup = programForCurrentFollowup;
	}
}
