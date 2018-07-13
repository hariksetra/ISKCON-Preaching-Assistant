package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;

@Repository
public interface FollowUpRepo extends PagingAndSortingRepository<FollowUp, Long> {
	long deleteByProgram(Program program);
	
	FollowUp findTop1ByProgramAndAttendeeAndVolunteerAndFollowupForSession(Program program, Devotee attendee, Devotee volunteer, ProgramSession programSession);
	
	List<FollowUp> findByProgram(Program program);
}