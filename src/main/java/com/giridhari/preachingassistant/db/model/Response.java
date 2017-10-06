package com.giridhari.preachingassistant.db.model;

public enum Response {
	
	CALL_AGAIN("CALL AGAIN"),
	COMING("COMING"),
	DOUBTFUL("DOUBTFUL"),
	NOT_COMING("NOT COMING"),
	WRONG_NUMBER("WRONG NUMBER"),
	NOT_REACHABLE("NOT REACHABLE"),
	WILL_COME_LATER("WILL COME LATER"),
	REMOVE_ME("REMOVE ME");
	
	private String response;
	
	private Response(String response) {
		this.response = response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return this.response;
	}
	
}