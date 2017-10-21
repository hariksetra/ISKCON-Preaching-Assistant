package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;

@Service
public interface DevoteeService {
	
	public List<Devotee> list();
	
	public Page<Devotee> list(Pageable pageable);
	
	public Devotee get(long devoteeId);
	
	public void create(Devotee devotee);
	
	public void update(Devotee devotee);
	
	public void delete(long devoteeId);
	
	public List<Devotee> getMyCapturedList (long devoteeId);
	
	public Page<Devotee> getMyCapturedList (long devoteeId, Pageable pageable);
	
	public Page<Devotee> searchDevotees(String query, Pageable pageable);
	
	public String getDisplayName(Devotee devotee);

}
