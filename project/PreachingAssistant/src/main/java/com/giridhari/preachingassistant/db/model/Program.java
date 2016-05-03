package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;

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

@Entity
@Table(name = "program", catalog = "preaching_assistant")
public class Program implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073830669851492366L;
	
	private Long id;
	private String name;
	private Devotee mentor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "mentor_id", columnDefinition = "integer")
	public Devotee getMentor() {
		return mentor;
	}
	
	public void setMentor(Devotee mentor) {
		this.mentor = mentor;
	}
	
}
