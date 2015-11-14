package com.giridhari.preachingassistant.api;

import com.google.api.server.spi.Constant;
import com.google.api.server.spi.config.Api;

@Api(name="devotee", version="v1", scopes = { Constant.API_EMAIL_SCOPE }, 
	clientIds = { Constant.API_EXPLORER_CLIENT_ID }, description = "API for Devotee information")
public class DevoteeApi {
	
}
