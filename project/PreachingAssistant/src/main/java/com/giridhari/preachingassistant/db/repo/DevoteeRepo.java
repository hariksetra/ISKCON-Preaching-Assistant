package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;

@Repository
public interface DevoteeRepo extends CrudRepository<Devotee, Long> {
	
}
