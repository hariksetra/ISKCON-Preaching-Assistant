package com.giridhari.preachingassistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.DevoteeHistory;

@Service
public interface DevoteeHistoryService {
	
	public List<DevoteeHistory> list();
	
	public DevoteeHistory getById(long devoteeHistoryId);
	
	public List<DevoteeHistory> getByRatedDevoteeId(long ratedDevoteeId);
	
	public List<DevoteeHistory> getByCommentedByDevoteeId(long commentedByDevoteeId);
	
	public void create(DevoteeHistory devoteeHistory);
	
	public void update(DevoteeHistory devoteeHistory);
	
	public void delete(long devoteeHistoryId);
	
	public String getCommentedByDevoteeName(DevoteeHistory devoteeHistory);
	
	public String getRatedByDevoteeName(DevoteeHistory devoteeHistory);
}
