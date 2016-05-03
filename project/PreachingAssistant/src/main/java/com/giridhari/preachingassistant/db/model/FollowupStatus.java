package com.giridhari.preachingassistant.db.model;

public enum FollowupStatus {
	
	COMING("COMING"),
	NOT_COMING("NOT_COMING"),
	DOUBTFUL("DOUBTFUL"),
	INVALID_NUMBER("INVALID_NUMBER");
	
	private String status;
	
	private FollowupStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}

}
