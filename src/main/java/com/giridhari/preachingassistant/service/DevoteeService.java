package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;

@Service
public interface DevoteeService {
	
	public List<Devotee> list();
	
	public Devotee get(long devoteeId);
	
	public void create(Devotee devotee);
	
	public void update(Devotee devotee);
	
	public void delete(long devoteeId);

}
