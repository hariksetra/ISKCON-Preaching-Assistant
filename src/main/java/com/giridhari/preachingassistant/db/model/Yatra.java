package com.giridhari.preachingassistant.db.model;

import java.io.Serializable;
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
import javax.persistence.Table;

@Entity
@Table(name = "yatra", catalog = "preaching_assistant")
public class Yatra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -431291515359070106L;
	
	private Long id;
	private String yatraName;
	private String yatraAddress;
	private YatraType yatraType;
	private Devotee yatraAdmin;
	private Set<Program> programsInYatra;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "integer", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "yatra_name", nullable = false)
	public String getYatraName() {
		return yatraName;
	}
	
	public void setYatraName(String yatraName) {
		this.yatraName = yatraName;
	}
	
	@Column(name = "yatra_address", nullable = true)
	public String getYatraAddress() {
		return yatraAddress;
	}
	
	public void setYatraAddress(String yatraAddress) {
		this.yatraAddress = yatraAddress;
	}
	
	@Column(name = "yatra_type", nullable = false)
	public YatraType getYatraType() {
		return yatraType;
	}
	
	public void setYatraType(YatraType yatraType) {
		this.yatraType = yatraType;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "yatra_admin", columnDefinition = "integer")
	public Devotee getYatraAdmin() {
		return yatraAdmin;
	}
	
	public void setYatraAdmin(Devotee yatraAdmin) {
		this.yatraAdmin = yatraAdmin;
	}

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="parentYatra")
	public Set<Program> getProgramsInYatra() {
		return programsInYatra;
	}

	public void setProgramsInYatra(Set<Program> programsInYatra) {
		this.programsInYatra = programsInYatra;
	}
}
