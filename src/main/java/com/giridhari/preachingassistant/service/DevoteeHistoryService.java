package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.DevoteeHistory;

@Service
public interface DevoteeHistoryService {
	
	public List<DevoteeHistory> list();
	
	public Page<DevoteeHistory> listAllByPage(Pageable pageable);
	
	public DevoteeHistory getById(long devoteeHistoryId);
	
	public List<DevoteeHistory> getByRatedDevoteeId(long ratedDevoteeId);
	
	public Page<DevoteeHistory> getByRatedDevoteeId(long ratedDevoteeId, Pageable pageable);
	
	public List<DevoteeHistory> getByCommentedByDevoteeId(long commentedByDevoteeId);
	
	public Page<DevoteeHistory> getByCommentedByDevoteeId(long commentedByDevoteeId, Pageable pageable);
	
	public void create(DevoteeHistory devoteeHistory);
	
	public void update(DevoteeHistory devoteeHistory);
	
	public void delete(long devoteeHistoryId);
	
	public String getCommentedByDevoteeName(DevoteeHistory devoteeHistory);
	
	public String getRatedByDevoteeName(DevoteeHistory devoteeHistory);
	
}
