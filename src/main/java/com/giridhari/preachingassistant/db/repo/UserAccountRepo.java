package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.UserAccount;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Long> {
	
	@Query("select ua from UserAccount ua where ua.username = ?1")
	public UserAccount findByUsername(@Param(value="username") String username);
	
}