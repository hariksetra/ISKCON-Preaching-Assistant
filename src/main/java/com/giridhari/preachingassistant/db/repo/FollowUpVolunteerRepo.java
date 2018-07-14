package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.Program;;

@Repository
public interface FollowUpVolunteerRepo extends PagingAndSortingRepository<FollowUpVolunteer, Long>{
	Page<FollowUpVolunteer> findByProgram_id(long programId, Pageable pageable);
	Page<FollowUpVolunteer> findByDevotee_id(long volunteerId, Pageable pageable);
	List<FollowUpVolunteer> findByDevotee_id(long volunteerId);
	List<FollowUpVolunteer> findByProgram(Program program);
	List<FollowUpVolunteer> findByProgram_id(long programId);
}
