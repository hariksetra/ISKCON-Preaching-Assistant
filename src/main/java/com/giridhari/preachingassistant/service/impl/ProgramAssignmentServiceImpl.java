package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.db.repo.ProgramAssignmentRepo;
import com.giridhari.preachingassistant.service.ProgramAssignmentService;

@Service
public class ProgramAssignmentServiceImpl implements ProgramAssignmentService {
	
	@Resource
	ProgramAssignmentRepo programAssignmentRepo;

	@Override
	public Page<ProgramAssignment> list(Pageable pageable) {
		return programAssignmentRepo.findAll(pageable);
	}

	@Override
	public ProgramAssignment get(long assignmentId) {
		return programAssignmentRepo.findOne(assignmentId);
	}

	@Override
	public void update(ProgramAssignment programAssignment) {
		programAssignmentRepo.save(programAssignment);

	}

	@Override
	public void delete(long assignmentId) {
		programAssignmentRepo.delete(assignmentId);
	}

	@Override
	public Page<ProgramAssignment> findByProgram(long programId, Pageable pageable) {
		return programAssignmentRepo.findByProgram_id(programId, pageable);
	}

	@Override
	public Page<ProgramAssignment> findByAttendee(long devoteeId, Pageable pageable) {
		return programAssignmentRepo.findByAttendee_id(devoteeId, pageable);
	}

	@Override
	public List<ProgramAssignment> findByProgram(Program program) {
		return programAssignmentRepo.findByProgram(program);
	}

	@Override
	public Page<ProgramAssignment> findProgramAssignmentTypeAheadByProgram(String typeText, Program program,
			Pageable pageable) {
		return programAssignmentRepo.findAllByAttendeeLegalNameContainingOrAttendeeInitiatedNameContainingOrAttendeeSmsPhoneContainingOrAttendeeEmailContainingAndProgram(typeText, typeText, typeText, typeText, program, pageable);
	}

	@Override
	public Page<ProgramAssignment> findProgramAssignmentTypeAheadByYatra(String typeText, Yatra yatra,
			Pageable pageable) {
		return programAssignmentRepo.findAllByAttendeeLegalNameContainingOrAttendeeInitiatedNameContainingOrAttendeeSmsPhoneContainingOrAttendeeEmailContainingAndProgramParentYatra(typeText, typeText, typeText, typeText, yatra, pageable);
	}
	
	@Override
	public long countByProgram(Program program) {
		return programAssignmentRepo.countByProgram(program);
	}

}
