package com.giridhari.preachingassistant.db.repo;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;

public interface ProgramSessionRepo extends PagingAndSortingRepository<ProgramSession, Long> {
	public Page<ProgramSession> findAllByProgram(Program program, Pageable pageable);
	
	public ProgramSession findByProgramAndSessionDate(Program program, Date sessionDate);
}
