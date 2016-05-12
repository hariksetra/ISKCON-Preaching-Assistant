package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.Repository;

import com.giridhari.preachingassistant.db.model.ProgramAssignment;

@org.springframework.stereotype.Repository
public interface ProgramAssignmentRepo extends Repository<ProgramAssignment, Long> {
	
}