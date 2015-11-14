package com.giridhari.preachingassistant.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Circle {

	@Id
	private String id;
	
	@Index
	private String name;
	
	@Parent
	private String mahaCircleId;

	public Circle(String id, String name, String mahaCircleId) {
		super();
		this.id = id;
		this.name = name;
		this.mahaCircleId = mahaCircleId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMahaCircleId() {
		return mahaCircleId;
	}
	
}
