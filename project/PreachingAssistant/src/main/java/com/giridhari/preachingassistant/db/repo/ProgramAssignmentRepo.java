package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;

@Repository
public interface ProgramAssignmentRepo extends PagingAndSortingRepository<ProgramAssignment, Long> {
	
	@Query("select pa from ProgramAssignment pa where pa.attendee = ?1")
	public Page<ProgramAssignment> findByAttendee(@Param("attendee") Devotee attendee, Pageable pageable);
	
	@Query("select pa from ProgramAssignment pa where pa.program = ?1")
	public Page<ProgramAssignment> findByProgram(@Param("program") Program program, Pageable pageable);
	
}