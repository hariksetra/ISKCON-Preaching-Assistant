package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.repo.FollowUpRepo;
import com.giridhari.preachingassistant.service.FollowUpService;

@Service
public class FollowUpServiceImpl implements FollowUpService {

	@Resource
	FollowUpRepo followUpRepo;
	
	@Override
	public Page<FollowUp> list(Pageable pageable) {
		return followUpRepo.findAll(pageable);
	}

	@Override
	public FollowUp get(long followUpId) {
		return followUpRepo.findOne(followUpId);
	}

	@Override
	public void update(FollowUp followUp) {
		followUpRepo.save(followUp);

	}

	@Override
	public void delete(long followUpId) {
		followUpRepo.delete(followUpId);
	}

}
