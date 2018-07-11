package com.giridhari.preachingassistant.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;

@Service
public interface ProgramSessionService {

	public Page<ProgramSession> list(Pageable pageable);
	
	public ProgramSession get(long id);
	
	public ProgramSession findSessionOfProgramByDate(Program program, Date sessionDate);
	
	public Page<ProgramSession> findByProgram(Program program, Pageable pageable);
	
	public void update(ProgramSession programSession);
	
	public void delete(long id);
}