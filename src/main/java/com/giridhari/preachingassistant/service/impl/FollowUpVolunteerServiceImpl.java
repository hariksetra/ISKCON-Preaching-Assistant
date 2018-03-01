package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.repo.FollowUpVolunteerRepo;
import com.giridhari.preachingassistant.service.FollowUpVolunteerService;

@Service
public class FollowUpVolunteerServiceImpl implements FollowUpVolunteerService {

	@Resource
	FollowUpVolunteerRepo followUpVolunteerRepo;
	
	@Override
	public Page<FollowUpVolunteer> list(Pageable pageable) {
		return followUpVolunteerRepo.findAll(pageable);
	}

	@Override
	public Page<FollowUpVolunteer> findByProgram(long programId, Pageable pageable) {
		return followUpVolunteerRepo.findByProgram_id(programId, pageable);
	}
	
	@Override
	public FollowUpVolunteer get(long id) {
		return followUpVolunteerRepo.findOne(id);
	}

	@Override
	public void update(FollowUpVolunteer followUpVolunteer) {
		followUpVolunteerRepo.save(followUpVolunteer);

	}

	@Override
	public void delete(long id) {
		followUpVolunteerRepo.delete(id);
	}

	@Override
	public List<FollowUpVolunteer> findByProgram(Program program) {
		return followUpVolunteerRepo.findByProgram(program);
	}

}
