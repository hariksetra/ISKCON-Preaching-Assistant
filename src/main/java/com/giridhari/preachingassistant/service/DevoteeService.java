package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Type;
import com.giridhari.preachingassistant.db.model.UserAccount;

@Service
public interface DevoteeService {
	
	public List<Devotee> list();
	
	public Page<Devotee> list(Pageable pageable);
	
	public Devotee get(long devoteeId);
	
	public void create(Devotee devotee);
	
	public void update(Devotee devotee);
	
	public void delete(long devoteeId);
	
	public Page<Devotee> searchDevotees(String query, Pageable pageable);
	
	public String getDisplayName(Devotee devotee);
	
	public Devotee findByEmail(String email);

	public Devotee findBySmsPhone(String smsPhone);
	
	public Page<Devotee> globalDevoteeSearchTypeAhead(String typeText, Pageable pageable);

	public Page<Devotee> devoteeSearch(String q, long programId, long yatraId, long devoteeId, Type role, Pageable pageable);
}
