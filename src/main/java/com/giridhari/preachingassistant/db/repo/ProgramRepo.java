package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.TargetAudience;

@Repository
public interface ProgramRepo extends PagingAndSortingRepository<Program, Long> {
	
	public Page<Program> findByMentor_id(long mentorId, Pageable pageable);
	
	public Page<Program> findByParentYatra_id(long yatraId, Pageable pageable);
	
	public Page<Program> findByType(ProgramType programType, Pageable pageable);
	
	public Page<Program> findByTargetAudience(TargetAudience targetAudience, Pageable pageable);
	
	public Page<Program> findByAreasSubscribed_ZipPostalCode(String zipPostalCode, Pageable pageable);
	
	public Page<Program> findByParticipants_Attendee_id(long devoteeId, Pageable pageable);
}