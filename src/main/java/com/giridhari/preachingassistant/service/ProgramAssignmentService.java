package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ProgramAssignment;;

@Service
public interface ProgramAssignmentService {

	public Page<ProgramAssignment> list(Pageable pageable);
	
	public ProgramAssignment get(long assignmentId);
	
	public void update(ProgramAssignment programAssignment);
	
	public void delete(long assignmentId);
}
