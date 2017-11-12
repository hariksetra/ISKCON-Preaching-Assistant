package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ProgramAreaSubscription;
import com.giridhari.preachingassistant.db.repo.ProgramAreaSubscriptionRepo;
import com.giridhari.preachingassistant.service.ProgramAreaSubscriptionService;

@Service
public class ProgramAreaSubscriptionServiceImpl implements ProgramAreaSubscriptionService {

	@Resource
	ProgramAreaSubscriptionRepo programAreaSubscriptionRepo;
	
	@Override
	public Page<ProgramAreaSubscription> list(Pageable pageable) {
		return programAreaSubscriptionRepo.findAll(pageable);
	}
	
	@Override
	public Page<ProgramAreaSubscription> getByProgramId(long programId, Pageable pageable){
		return programAreaSubscriptionRepo.findByProgramId_Id(programId, pageable);
	}
	@Override
	public ProgramAreaSubscription get(long subscriptionId) {
		return programAreaSubscriptionRepo.findOne(subscriptionId);
	}

	@Override
	public void update(ProgramAreaSubscription programAreaSubscription) {
		programAreaSubscriptionRepo.save(programAreaSubscription);

	}

	@Override
	public void delete(long subscriptionId) {
		programAreaSubscriptionRepo.delete(subscriptionId);

	}

}
