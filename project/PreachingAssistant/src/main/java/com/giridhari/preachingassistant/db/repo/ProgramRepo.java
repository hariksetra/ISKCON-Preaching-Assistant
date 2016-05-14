package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Program;

@Repository
public interface ProgramRepo extends CrudRepository<Program, Long> {
	
}