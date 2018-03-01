package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.model.Program;

@Service
public interface FollowUpService {

	public Page<FollowUp> list(Pageable pageable);
	
	public FollowUp get(long followUpId);
	
	public void update(FollowUp followUp);
	
	public void delete(long followUpId);
	
	public long clearFollowupOfProgram(Program program);
	
	public FollowUp getFollowUpRecord(Program program, Devotee attendee, Devotee volunteer);
	
	public List<FollowUp> findByProgram(Program program);
}