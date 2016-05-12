package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.Repository;

import com.giridhari.preachingassistant.db.model.Program;

@org.springframework.stereotype.Repository
public interface ProgramRepo extends Repository<Program, Long> {
	
}