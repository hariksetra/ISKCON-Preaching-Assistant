package com.giridhari.preachingassistant.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Sector {

	@Id
	private String id;
	
	@Index
	private String name;
	
	@Parent
	private String circleId;
	
	private String leaderId;

	public Sector(String id, String name, String circleId, String leaderId) {
		super();
		this.id = id;
		this.name = name;
		this.circleId = circleId;
		this.leaderId = leaderId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCircleId() {
		return circleId;
	}

	public String getLeaderId() {
		return leaderId;
	}
	
}
