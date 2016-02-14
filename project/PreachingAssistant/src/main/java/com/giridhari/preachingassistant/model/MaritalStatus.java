package com.giridhari.preachingassistant.model;

public enum MaritalStatus {
	MARRIED("MARRIED"),
	SINGLE("SINGLE"),
	DIVORCED("DIVORCED");
	
	private final String maritalStatus;
	
	private MaritalStatus(final String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	@Override
	public String toString() {
		return maritalStatus;
	}
}
