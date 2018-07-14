package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.exception.AssignerNotFoundException;

@Service
public interface FollowUpAssignmentService {

	public Page<FollowUpAssignment> list(Pageable pageable);
	
	public Page<FollowUpAssignment> listByVolunteer(Devotee volunteer, Pageable pageable);
	
	public Page<FollowUpAssignment> listByVolunteerAndProgram(Devotee volunteer, Program program, Pageable pageable);
	
	public Page<FollowUpAssignment> listByProgram(Program program, Pageable pageable);
	
	public List<FollowUpAssignment> listOfProgramsForVolunteer(Devotee volunteer);
	
	public List<FollowUpAssignment> listOfAssignmentsOfProgram(Program program);

	public void autoAssign(Program program, String strategy) throws AssignerNotFoundException;
	
	public FollowUpAssignment get(long assignmentId);
	
	public void update(FollowUpAssignment followUpAssignment);
	
	public void delete(long assignmentId);
	
	public Long deleteAssignmentsOfProgram(Program program);
}
