package com.giridhari.preachingassistant.service;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.TargetAudience;

@Service
public interface ProgramService {
	
	public Page<Program> list(Pageable pageable);
	
	public Page<Program> findByMentorId(long mentorId, Pageable pageable);
	
	public Page<Program> findByYatraId(long yatraId, Pageable pageable);
	
	public Page<Program> findByProgramType(ProgramType programType, Pageable pageable);
	
	public Page<Program> findByTargetAudience(TargetAudience targetAudience, Pageable pageable);
	
	public Page<Program> findByZipPostalCode(String zipPostalCode, Pageable pageable);
	
	public Page<Program> findByAttendee(long attendeeId, Pageable pageable);
	
	public Page<Program> searchProgram(HashMap<String, String> requestData, Pageable pageable);
	
	public Program get(long programId);
	
	public void update(Program program);
	
	public void delete(long programId);
}
