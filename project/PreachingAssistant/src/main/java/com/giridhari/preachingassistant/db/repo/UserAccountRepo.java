package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.giridhari.preachingassistant.db.model.UserAccount;

@org.springframework.stereotype.Repository
public interface UserAccountRepo extends Repository<UserAccount, Long> {
	
	public List<UserAccount> findAll();
	
	public UserAccount save(UserAccount userAccount);
	
}