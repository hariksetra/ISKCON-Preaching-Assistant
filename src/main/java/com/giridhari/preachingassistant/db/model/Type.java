package com.giridhari.preachingassistant.db.model;

import org.springframework.security.core.GrantedAuthority;

public enum Type implements GrantedAuthority {
	
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

	@Override
	public String getAuthority() {
		return this.type;
	}

}
