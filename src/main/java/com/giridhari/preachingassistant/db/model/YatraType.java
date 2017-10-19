package com.giridhari.preachingassistant.db.model;

public enum YatraType {
	ISKCON("ISKCON"),
	COUNTRY("Country"),
	ZONE("Zone"),
	TEMPLE("Temple"),
	EXTENSION_CENTER("Extension Center"),
	PREACHING_CENTER("Preaching Center"),
	NAMAHATTA("Namahatta"),
	UNRECOGNISED_CENTER("Unrecognised Center");
	
	private String yatraType;
	
	private YatraType(String yatraType) {
		this.yatraType = yatraType;
	}
	
	@Override
	public String toString() {
		return this.yatraType;
	}
	
}
