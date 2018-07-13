package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;
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

	@Override
	public long clearFollowupOfProgram(Program program) {
		return followUpRepo.deleteByProgram(program);
	}

	@Override
	public FollowUp getFollowUpRecord(Program program, Devotee attendee, Devotee volunteer, ProgramSession programSession) {
		return followUpRepo.findTop1ByProgramAndAttendeeAndVolunteerAndFollowupForSession(program, attendee, volunteer, programSession);
	}

	@Override
	public List<FollowUp> findByProgram(Program program) {
		return followUpRepo.findByProgram(program);
	}

}
