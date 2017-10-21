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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giridhari.preachingassistant.model.CountryCode;

@Entity
@Table(name = "program_area_subscription", catalog = "preaching_assistant")
public class ProgramAreaSubscription {
	
	
	private Long id;
	private Program programId;
	private CountryCode countryCode;
	private String zipPostalCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id", columnDefinition = "integer")
	@JsonManagedReference
	public Program getProgramId() {
		return programId;
	}
	
	public void setProgramId(Program programId) {
		this.programId = programId;
	}
	
	@Column(name = "country_code", nullable = false)
	public CountryCode getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	
	@Column(name = "zip_postal_code", nullable = false)
	public String getZipPostalCode() {
		return zipPostalCode;
	}
	
	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}
}
