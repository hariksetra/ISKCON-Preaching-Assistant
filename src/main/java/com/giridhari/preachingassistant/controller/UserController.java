package com.giridhari.preachingassistant.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.db.model.mapper.UserAccountMapper;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.useraccount.CreateUserAccountRequestEntity;
import com.giridhari.preachingassistant.rest.model.useraccount.UserAccountPasswordChangeRequestEntity;
import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponseEntity;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.UserService;
import com.giridhari.preachingassistant.util.BadRequestException;
import com.giridhari.preachingassistant.util.ForbiddenException;
import com.giridhari.preachingassistant.util.NotFoundException;

@RestController
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private DevoteeService devoteeService;

	@RequestMapping(name = "devoteeDetail", value="/login", method = RequestMethod.GET)
	public BaseDataResponse get(@RequestParam("username") String username) {
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

	@RequestMapping(name = "changePassword", value="/changePassword/{devoteeId}", method = RequestMethod.PUT)
	@ResponseBody
	public BaseDataResponse changePassword(@PathVariable("devoteeId") long devoteeId, @RequestBody UserAccountPasswordChangeRequestEntity requestData) {
		UserAccount userAccount;
		Devotee devotee;

		userAccount = userService.getByDevoteeId(devoteeId);

		String oldPassword = requestData.getOldPassword();
		String newPassword = requestData.getNewPassword();
		if (userAccount != null && userAccount.getPassword().equals(oldPassword)) {
			userAccount.setPassword(newPassword);
			userService.update(userAccount);
			devotee = userAccount.getProfile();
			UserLoginResponseEntity responseData = UserAccountMapper.convertToLoginUserResponseEntity(userAccount, devotee);
			return new BaseDataResponse(responseData);
		} else {
			throw new ForbiddenException("wrong password enetered");
		}
	}

	@RequestMapping(name = "createUserAccount", value="/userAccount/", method = RequestMethod.POST)
	@ResponseBody
	public BaseDataResponse create(@RequestBody CreateUserAccountRequestEntity requestData) {
		// check that user account doesn't exist
		long devoteeId = requestData.getDevoteeId();

		if (userService.getByDevoteeId(devoteeId) != null) {
			throw new BadRequestException("user account for devotee already exists");
		}

		Devotee devotee = devoteeService.get(devoteeId);
		String email = requestData.getEmail();
                // TODO - put this password in a constant somewhere
		String password = "harekrishna";
		String type = requestData.getType();
		UserAccount userAccount = userService.createForDevotee(devotee, email, password, type);

		devotee.setUserAccount(userAccount);
		devotee.setEmail(email);
		devoteeService.update(devotee);

		UserLoginResponseEntity responseData = UserAccountMapper.convertToLoginUserResponseEntity(userAccount, devotee);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "resetPassword", value="/resetPassword/{userAccountId}", method = RequestMethod.PUT)
	public BaseDataResponse resetPassword(@PathVariable("userAccountId") long userAccountId) {
		UserAccount userAccount = userService.getById(userAccountId);

		if (userAccount == null) {
			throw new NotFoundException("Given user account not found");
		}

		// TODO - put this password as a constant somewhere
		String newPassword = "harekrishna";
		userAccount.setPassword(newPassword);
		userService.update(userAccount);

		Devotee devotee = userAccount.getProfile();
		UserLoginResponseEntity responseData = UserAccountMapper.convertToLoginUserResponseEntity(userAccount, devotee);
		return new BaseDataResponse(responseData);
	}
}
