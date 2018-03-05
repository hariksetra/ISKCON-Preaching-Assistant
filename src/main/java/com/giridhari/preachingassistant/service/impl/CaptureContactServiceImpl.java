package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.repo.CaptureContactRepo;
import com.giridhari.preachingassistant.service.CaptureContactService;

@Service
public class CaptureContactServiceImpl implements CaptureContactService {

	@Resource
	CaptureContactRepo captureContactRepo;
	
	@Override
	public Page<CaptureContact> list(Pageable pageable) {
		return captureContactRepo.findAll(pageable);
	}

	@Override
	public CaptureContact get(long captureContactId) {
		return captureContactRepo.findOne(captureContactId);
	}

	@Override
	public void update(CaptureContact captureContact) {
		captureContactRepo.save(captureContact);
		
	}

	@Override
	public void delete(long captureContactId) {
		captureContactRepo.delete(captureContactId);
		
	}

	@Override
	public Page<CaptureContact> findByCapturedBy(long devoteeId, Pageable pageable) {
		return captureContactRepo.findByCapturedBy_id(devoteeId, pageable);
	}

	@Override
	public CaptureContact findByCapturedByAndCapturedDevotee(Devotee capturedBy, Devotee capturedDevotee) {
		return captureContactRepo.findByCapturedByAndCapturedDevotee(capturedBy, capturedDevotee);
	}
}
