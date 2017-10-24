package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Yatra;

@Service
public interface YatraService {
	
	public List<Yatra> list();
	
	public Page<Yatra> list(Pageable pageable);
	
	public Page<Yatra> getYatraByAdminId(long adminId, Pageable pageable);
	
	public Yatra getById(long yatraId);
	
	public void create(Yatra yatra);
	
	public void update(Yatra yatra);
	
	public void delete(long yatraId);
	
}
