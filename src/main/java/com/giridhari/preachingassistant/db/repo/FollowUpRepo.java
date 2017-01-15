package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.FollowUp;

@Repository
public interface FollowUpRepo extends CrudRepository<FollowUp, Long> {
	
}