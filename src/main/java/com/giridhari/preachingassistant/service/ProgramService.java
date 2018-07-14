package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;

@Service
public interface ProgramService {
	
	public Page<Program> list(Pageable pageable);
	
	public Page<Program> findByMentorId(long mentorId, Pageable pageable);
	
	public List<Program> findByMentorId(long mentorId);
	
	public Page<Program> findByYatraId(long yatraId, Pageable pageable);
	
	public Program findByCurrentFollowupSession(long sessionId);
	
	public Program get(long programId);
	
	public void update(Program program);
	
	public void delete(long programId);
}
