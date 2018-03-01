package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.Devotee;

@Service
public interface CaptureContactService {
	public Page<CaptureContact> list(Pageable pageable);
	
	public CaptureContact get(long captureContactId);
	
	public void update(CaptureContact captureContact);
	
	public void delete(long captureContactId);
	
	public Page<CaptureContact> findByCapturedBy(long devoteeId, Pageable pageable);
	
	public CaptureContact findByCapturedByAndCapturedDevotee(Devotee capturedBy, Devotee capturedDevotee);
}
