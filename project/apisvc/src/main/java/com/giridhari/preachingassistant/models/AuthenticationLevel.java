package com.giridhari.preachingassistant.models;

public enum AuthenticationLevel {
	
	TEMPLE_PRESIDENT("TP"),
	MAHA_CIRCLE_LEADER("MCL"),
	CIRCLE_LEADER("CL"),
	SECTOR_SERVANT("SS"),
	BHAKTI_VRKSHA("BV");
	
	private String authenticationLevel;
	
	private AuthenticationLevel(String authenticationLevel) {
		this.authenticationLevel = authenticationLevel;
	}
	
	public String get() {
		return this.authenticationLevel;
	}

}
