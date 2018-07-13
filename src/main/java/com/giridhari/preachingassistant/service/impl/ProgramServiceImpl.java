package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.repo.ProgramRepo;
import com.giridhari.preachingassistant.service.ProgramService;

@Service
public class ProgramServiceImpl implements ProgramService{
	
	@Resource
	ProgramRepo programRepo;
	
	@Override
	public Page<Program> list(Pageable pageable) {
		return programRepo.findAll(pageable);
	}
	
	@Override
	public Page<Program> findByMentorId(long mentorId, Pageable pageable) {
		return programRepo.findByMentor_id(mentorId, pageable);
	}
	
	@Override
	public Page<Program> findByYatraId(long yatraId, Pageable pageable) {
		return programRepo.findByParentYatra_id(yatraId, pageable);
	}
	
	@Override
	public Program findByCurrentFollowupSession(long sessionId) {
		return programRepo.findByCurrentFollowupSession_id(sessionId);
	}
	
	@Override
	public Program get(long programId) {
		return programRepo.findOne(programId);
	}
	
	@Override
	public void update(Program program) {
		programRepo.save(program);
	}
	
	@Override
	public void delete(long programId) {
		programRepo.delete(programId);
	}
	
}
