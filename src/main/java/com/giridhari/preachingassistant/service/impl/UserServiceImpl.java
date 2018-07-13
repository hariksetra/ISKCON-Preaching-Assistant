/**
 *
 */
package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.db.repo.UserAccountRepo;
import com.giridhari.preachingassistant.service.UserService;

/**
 * @author Hariksetra das
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserAccountRepo userRepo;

	@Override
	public UserAccount get(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public UserAccount getByDevoteeId(long devoteeId) {
		return userRepo.findByProfile_id(devoteeId);
	}

	@Override
	public void update(UserAccount userAccount) {
		userRepo.save(userAccount);
	}
}
