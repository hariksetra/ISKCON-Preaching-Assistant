package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ProgramAreaSubscription;

@Service
public interface ProgramAreaSubscriptionService {
	
	public Page<ProgramAreaSubscription> list(Pageable pageable);
	
	public Page<ProgramAreaSubscription> findByProgramId(long programId, Pageable pageable);
	
	public ProgramAreaSubscription get(long subscriptionId);
	
	public void update(ProgramAreaSubscription programAreaSubscription);
	
	public void delete(long subscriptionId);
}
