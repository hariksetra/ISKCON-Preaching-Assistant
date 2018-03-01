package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;;

@Service
public interface ProgramAssignmentService {

	public Page<ProgramAssignment> list(Pageable pageable);
	
	public ProgramAssignment get(long assignmentId);
	
	public Page<ProgramAssignment> findByProgram(long programId, Pageable pageable);
	
	public Page<ProgramAssignment> findByAttendee(long devoteeId, Pageable pageable);
	
	public void update(ProgramAssignment programAssignment);
	
	public void delete(long assignmentId);
	
	public List<ProgramAssignment> findByProgram(Program program);
}
