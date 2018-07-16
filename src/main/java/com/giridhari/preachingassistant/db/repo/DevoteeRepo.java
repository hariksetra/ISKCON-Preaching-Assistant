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
			+ "d.initiatedName like concat('%', ?1, '%') or d.legalName like concat('%', ?1, '%') "
			+ "or d.area like concat('%', ?1, '%') or d.smsPhone like concat('%', ?1, '%')")
	public Page<Devotee> findByQuery(
			@Param(value = "query") String query, 
			Pageable pageable);
	
	@Query("select distinct d from Devotee d where d not in (select distinct pa.attendee from ProgramAssignment pa)")
	public Page<Devotee> unassignedDevotees(Pageable pageable);
	
	public Devotee findByEmail(String email);
	public Devotee findBySmsPhone(String smsPhone);	
	
	/*Query for finding devotees not attending any of the program*/
	//public List<Devotee>
	//findAllByLegalNameContainingOrInitiatedNameContainingOrSmsPhoneContainingAndAttendingPrograms
	//(String legalName, String initiatedName, String smsPhone, null);
	
	/*Query for global devotee search*/
	public Page<Devotee>
	findAllByLegalNameContainingOrInitiatedNameContainingOrSmsPhoneContainingOrEmailContaining
	(String legalName, String initiatedName, String smsPhone, String email, Pageable pageable);

	@Query("select distinct d from Devotee d "
			+ "left join d.attendingPrograms pa left join pa.program p1 left join p1.parentYatra y1 "
			+ "left join d.followUps fv left join fv.program p2 left join p2.parentYatra y2 "
			+ "left join d.mentoredPrograms p3 left join p3.parentYatra y3 "
			+ "left join d.capturedBy cc left join cc.capturedBy cb "
			+ "where d.legalName like concat('%', ?1, '%') and "
			+ "(y1.id in ?2 or y2.id in ?2 or y3.id in ?2 or cb.id = ?3)")
	public Page<Devotee> findDevoteesForUserSearch(String q, List<Long> yatraIds, long devoteeId, Pageable pageable);

	@Query("select distinct d from Devotee d "
			+ "left join d.capturedBy cc left join cc.capturedBy cb "
			+ "where d.legalName like concat('%', ?1, '%') and "
			+ "cb.id = ?2")
	public Page<Devotee> findDevoteesInUserCapturedList(String q, long devoteeId, Pageable pageable);
}
