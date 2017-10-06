package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.Devotee;

@Repository
public interface DevoteeHistoryRepo extends CrudRepository<DevoteeHistory, Long> {
	
	public List<DevoteeHistory> findByFollowUpVolunteer_id(long id);
	
	public List<DevoteeHistory> findByFollowUpVolunteer(FollowUpVolunteer followUpVolunteer);
	
	public List<DevoteeHistory> findByDevotee_id(long id);
	
	public List<DevoteeHistory> findByDevotee(Devotee devotee);
}
