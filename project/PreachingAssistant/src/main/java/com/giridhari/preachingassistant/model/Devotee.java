package com.giridhari.preachingassistant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DevoteeInfo")
public class Devotee {

	@Id
	@Column(name="DIDevoteeID")
	private long id;
	
	@Column(name="DILegalName", length=50)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DIDOB", columnDefinition="date")
	private Date dob;	
	
}
