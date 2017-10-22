package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;

@Service
public interface ProgramService {
	
	public Page<Program> list(Pageable pageable);
	
	public Program get(long programId);
	
	public void update(Program program);
	
	public void delete(long programId);
}
