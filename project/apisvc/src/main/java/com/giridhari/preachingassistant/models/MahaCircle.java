package com.giridhari.preachingassistant.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class MahaCircle {
	
	@Id
	private String id;
	
	@Index
	private String name;
	
	@Parent
	private String templePresidentId;
	
	private String leaderId;

	public MahaCircle(String id, String name, String templePresidentId, String leaderId) {
		super();
		this.id = id;
		this.name = name;
		this.templePresidentId = templePresidentId;
		this.leaderId = leaderId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTemplePresidentId() {
		return templePresidentId;
	}
	
	public String getLeaderId() {
		return leaderId;
	}

}
