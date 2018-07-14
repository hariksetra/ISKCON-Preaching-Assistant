package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.Program;

@Repository
public interface FollowUpAssignmentRepo extends PagingAndSortingRepository<FollowUpAssignment, Long> {
	public List<FollowUpAssignment> findByVolunteer(Devotee volunteer);

	public Page<FollowUpAssignment> findByVolunteer(Devotee volunteer, Pageable pageable);
	
	public Page<FollowUpAssignment> findByVolunteerAndProgram(Devotee volunteer, Program program, Pageable pageable);
	
	public List<FollowUpAssignment> findByVolunteerAndProgram(Devotee volunteer, Program program);
	
	public Page<FollowUpAssignment> findByProgram(Program program, Pageable pageable);
	
	public List<FollowUpAssignment> findByProgram(Program program);
	
	public Long deleteByProgram(Program program);
	
	public long countByProgram(Program program);
	
	public long countByProgramAndVolunteer(Program program, Devotee devotee);
	
}