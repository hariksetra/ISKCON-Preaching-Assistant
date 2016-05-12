package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;

@org.springframework.stereotype.Repository
public interface DevoteeRepo extends Repository<Devotee, Long> {

	public List<Devotee>  findAll();
	
	public Devotee save(Devotee devotee);
	
	public Devotee findOne(Long id);
	
	public void delete(Long id);
	
}
