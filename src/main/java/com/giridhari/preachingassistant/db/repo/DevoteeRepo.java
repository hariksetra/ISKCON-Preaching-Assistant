package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;

import java.util.List;

@Repository
public interface DevoteeRepo 
	extends PagingAndSortingRepository<Devotee, Long> {
	
	@Query("select distinct d from Devotee d where "
			+ "d.initiatedName = ?1 or d.legalName = ?1 "
			+ "or d.area = ?1 or d.smsPhone = ?1 "
			+ "or d.designation = ?1")
	public Page<Devotee> findByQuery(
			@Param(value = "query") String query, 
			Pageable pageable);
	
	
	@Query("select distinct d from Devotee d where d.capturedBy.id = ?1")
	public List<Devotee> findByCapturedBy(
			@Param(value = "captured_by") long devoteeId);
	
	@Query("select distinct d from Devotee d where d not in (select distinct pa.attendee from ProgramAssignment pa)")
	public Page<Devotee> unassignedDevotees(Pageable pageable);
	
}
