package com.giridhari.preachingassistant.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.db.repo.ProgramSessionRepo;
import com.giridhari.preachingassistant.service.ProgramSessionService;

@Service
public class ProgramSessionServiceImpl implements ProgramSessionService {

	@Resource
	ProgramSessionRepo programSessionRepo;
	
	@Override
	public Page<ProgramSession> list(Pageable pageable) {
		return programSessionRepo.findAll(pageable);
	}

	@Override
	public ProgramSession get(long id) {
		return programSessionRepo.findOne(id);
	}

	@Override
	public void update(ProgramSession programSession) {
		programSessionRepo.save(programSession);

	}

	@Override
	public void delete(long id) {
		programSessionRepo.delete(id);
	}

	@Override
	public ProgramSession findSessionOfProgramByDate(Program program, Date sessionDate) {
		return programSessionRepo.findByProgramAndSessionDate(program, sessionDate);
	}

	@Override
	public Page<ProgramSession> findByProgram(Program program, Pageable pageable) {
		return programSessionRepo.findAllByProgram(program, pageable);
	}

}