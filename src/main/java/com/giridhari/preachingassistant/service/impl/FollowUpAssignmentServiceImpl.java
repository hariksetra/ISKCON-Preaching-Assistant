package com.giridhari.preachingassistant.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.repo.FollowUpAssignmentRepo;
import com.giridhari.preachingassistant.service.FollowUpAssignmentService;

@Service
public class FollowUpAssignmentServiceImpl implements FollowUpAssignmentService {

	@Resource
	FollowUpAssignmentRepo followupAssignmentRepo;
	
	@Override
	public Page<FollowUpAssignment> list(Pageable pageable) {
		return followupAssignmentRepo.findAll(pageable);
	}
	
	@Override
	public Page<FollowUpAssignment> listByVolunteer(Devotee volunteer, Pageable pageable) {
		return followupAssignmentRepo.findByVolunteer(volunteer, pageable);
	}
	
	@Override
	public Page<FollowUpAssignment> listByVolunteerAndProgram(Devotee volunteer, Program program, Pageable pageable) {
		return followupAssignmentRepo.findByVolunteerAndProgram(volunteer, program, pageable);
	}
	
	@Override
	public Page<FollowUpAssignment> listByProgram(Program program, Pageable pageable) {
		return followupAssignmentRepo.findByProgram(program, pageable);
	}
	
	@Override
	public List<FollowUpAssignment> listOfProgramsForVolunteer(Devotee volunteer) {
		List<FollowUpAssignment> listOfFollowUpAssignments = followupAssignmentRepo.findByVolunteer(volunteer);
		
		// extract only the unique programs from this list
		Set<Long> includedProgramIds = new HashSet<Long>();
		// defining list of followup assignments having distinct programs
		List<FollowUpAssignment> listOfDistinctFollowUpAssignments = new ArrayList<FollowUpAssignment>();
		
		Iterator<FollowUpAssignment> iterator = listOfFollowUpAssignments.iterator();
		
		FollowUpAssignment curFollowUpAssignment;
		while (iterator.hasNext()) {
			curFollowUpAssignment = iterator.next();
			long programId = curFollowUpAssignment.getProgram().getId();
			if (includedProgramIds.contains(programId)) {
				continue;
			}
			includedProgramIds.add(programId);
			listOfDistinctFollowUpAssignments.add(curFollowUpAssignment);
		}
		return listOfDistinctFollowUpAssignments;
	}

	@Override
	public FollowUpAssignment get(long assignmentId) {
		return followupAssignmentRepo.findOne(assignmentId);
	}

	@Override
	public void update(FollowUpAssignment followUpAssignment) {
		followupAssignmentRepo.save(followUpAssignment);

	}

	@Override
	public void delete(long assignmentId) {
		followupAssignmentRepo.delete(assignmentId);
	}

	@Override
	public Long deleteAssignmentsOfProgram(Program program) {
		return followupAssignmentRepo.deleteByProgram(program);
	}

	@Override
	public List<FollowUpAssignment> listOfAssignmentsOfProgram(Program program) {
		return followupAssignmentRepo.findByProgram(program);
	}

}
