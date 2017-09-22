package com.giridhari.preachingassistant.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.db.model.mapper.UserAccountMapper;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponseEntity;
import com.giridhari.preachingassistant.service.UserService;

@RestController
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(name = "devoteeDetail", value="/login/{username}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("username") String username) {
		UserAccount userAccount;
		Devotee devotee;
		
		userAccount = userService.get(username);
		if (userAccount!=null) {
			devotee = userAccount.getProfile();
			UserLoginResponseEntity responseData = UserAccountMapper.convertToLoginUserResponseEntity(userAccount, devotee);
			return new BaseDataResponse(responseData);
		}
		
		return null;
	}
}
