package com.giridhari.preachingassistant.db.model;

public enum Type {
	
	MEMBER("MEMBER"),
	ADMIN("ADMIN");
	
	private String type;
	
	private Type(final String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}

}
