package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;

@Repository
public interface ProgramRepo extends PagingAndSortingRepository<Program, Long> {

	@Query("select p from Program p where p.mentor = ?1")
	public Page<Program> findByMentor(@Param("mentor") Devotee mentor, Pageable pageable);
	
}