package com.giridhari.preachingassistant.db.model;

public enum IncomeScale {
	
	HIGH("HIGH"),
	MID("MID"),
	LOW("LOW");
	
	private String scale;
	
	private IncomeScale(String scale) {
		this.scale = scale;
	}
	
	@Override
	public String toString() {
		return this.scale;
	}

}
