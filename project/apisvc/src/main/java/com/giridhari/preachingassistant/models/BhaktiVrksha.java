package com.giridhari.preachingassistant.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class BhaktiVrksha {
	
	@Id
	private String id;
	
	@Index
	private String name;
	
	@Parent
	private String sectorId;

	public BhaktiVrksha(String id, String name, String sectorId) {
		super();
		this.id = id;
		this.name = name;
		this.sectorId = sectorId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSectorId() {
		return sectorId;
	}

}
