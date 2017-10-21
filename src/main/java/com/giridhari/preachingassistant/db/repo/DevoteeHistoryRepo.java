package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;

@Repository
public interface DevoteeHistoryRepo extends PagingAndSortingRepository<DevoteeHistory, Long> {
	
	public List<DevoteeHistory> findByCommentedByDevotee_id(long id);
	
	public Page<DevoteeHistory> findByCommentedByDevotee_id(long id, Pageable pageable);
	
	public List<DevoteeHistory> findByCommentedByDevotee(Devotee commentedByDevotee);
	
	public Page<DevoteeHistory> findByCommentedByDevotee(Devotee commentedByDevotee, Pageable pageable);
	
	public List<DevoteeHistory> findByRatedDevotee_id(long id);
	
	public Page<DevoteeHistory> findByRatedDevotee_id(long id, Pageable pageable);
	
	public List<DevoteeHistory> findByRatedDevotee(Devotee ratedDevotee);
	
	public Page<DevoteeHistory> findByRatedDevotee(Devotee ratedDevotee, Pageable pageable);
}
