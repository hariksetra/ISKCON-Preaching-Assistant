package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Program;

@Repository
public interface ProgramRepo extends PagingAndSortingRepository<Program, Long> {
	
	public Page<Program> findByMentor_id(long mentorId, Pageable pageable);
	
	public List<Program> findByMentor_id(long mentorId);
	
	public Page<Program> findByParentYatra_id(long yatraId, Pageable pageable);
	
	public Program findByCurrentFollowupSession_id(long sessionId); 
	
}