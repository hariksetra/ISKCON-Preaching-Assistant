package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.UserAccount;

@Service
public interface UserService {
	public UserAccount get(String username);

	public UserAccount getByDevoteeId(long devoteeId);

	public void update(UserAccount userAccount);

	public UserAccount createForDevotee(Devotee devotee, String email, String password, String type);
}
