package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.ProgramAreaSubscription;

@Repository
public interface ProgramAreaSubscriptionRepo extends PagingAndSortingRepository<ProgramAreaSubscription,Long> {
	Page<ProgramAreaSubscription> findByProgramId_id(long programId, Pageable pageable);
}
