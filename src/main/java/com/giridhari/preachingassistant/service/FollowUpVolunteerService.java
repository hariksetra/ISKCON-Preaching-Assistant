package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.Program;

@Service
public interface FollowUpVolunteerService {

	public Page<FollowUpVolunteer> list(Pageable pageable);
	
	public Page<FollowUpVolunteer> findByProgram(long programId, Pageable pageable);
	
	public FollowUpVolunteer get(long id);
	
	public void update(FollowUpVolunteer followUpVolunteer);
	
	public void delete(long id);
	
	public List<FollowUpVolunteer> findByProgram(Program program);
}
