package com.giridhari.preachingassistant.db.model.mapper;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponseEntity;

public class UserAccountMapper {
	public static UserLoginResponseEntity convertToLoginUserResponseEntity(UserAccount userAccount, Devotee devotee) {
		UserLoginResponseEntity responseData = new UserLoginResponseEntity();
		
		responseData.setDevoteeId(devotee.getId());
		responseData.setDevoteeName(devotee.getLegalName());
		responseData.setRole(userAccount.getType().toString());
		return responseData;
	}
}
