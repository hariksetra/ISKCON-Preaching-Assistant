package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.ProgramAssignment;

@Repository
public interface ProgramAssignmentRepo extends CrudRepository<ProgramAssignment, Long> {
	
}