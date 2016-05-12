package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.Repository;

import com.giridhari.preachingassistant.db.model.FollowUp;

@org.springframework.stereotype.Repository
public interface FollowUpRepo extends Repository<FollowUp, Long> {
	
}