package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUp;

@Service
public interface FollowUpService {

	public Page<FollowUp> list(Pageable pageable);
	
	public FollowUp get(long followUpId);
	
	public void update(FollowUp followUp);
	
	public void delete(long followUpId);
}