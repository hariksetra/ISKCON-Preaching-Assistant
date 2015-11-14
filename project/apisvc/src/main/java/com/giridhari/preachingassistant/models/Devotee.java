package com.giridhari.preachingassistant.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Devotee {
	
	@Id
	private String id;
	
	private String primaryEmailId;
	
	@Index
	private String legalName;
	
	@Index
	private String initiatedName;
	
	private String primaryPhone;
	
	private String address;
	
	private String otherInfo;
	
	@Index
	private AuthenticationLevel higestAuthenticationLevel;
	
	@Parent
	private String relatedParentElementId;

	public Devotee(String id, String primaryEmailId, String legalName,
			String initiatedName, String primaryPhone, String address,
			String otherInfo, AuthenticationLevel higestAuthenticationLevel,
			String relatedParentElementId) {
		super();
		this.id = id;
		this.primaryEmailId = primaryEmailId;
		this.legalName = legalName;
		this.initiatedName = initiatedName;
		this.primaryPhone = primaryPhone;
		this.address = address;
		this.otherInfo = otherInfo;
		this.higestAuthenticationLevel = higestAuthenticationLevel;
		this.relatedParentElementId = relatedParentElementId;
		
	}

	public String getId() {
		return id;
	}

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public String getLegalName() {
		return legalName;
	}

	public String getInitiatedName() {
		return initiatedName;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public String getAddress() {
		return address;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public AuthenticationLevel getHigestAuthenticationLevel() {
		return higestAuthenticationLevel;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("{ Devotee ID: ").append(this.id).append(", ")
			.append("Primary Email: ").append(this.primaryEmailId).append(", ")
			.append("Legal Name: ").append(this.legalName).append(", ")
			.append("Initiated Name: ").append(this.initiatedName).append(", ")
			.append("Address: ").append(this.address).append(", ")
			.append("Primary Phone: ").append(this.primaryPhone).append(", ")
			.append("Other Information: ").append(this.otherInfo).append(" }")
			.toString();
	}

}
