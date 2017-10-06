package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.repo.DevoteeHistoryRepo;
import com.giridhari.preachingassistant.service.DevoteeHistoryService;

@Service
public class DevoteeHistoryServiceImpl implements DevoteeHistoryService{

	@Resource
	DevoteeHistoryRepo devoteeHistoryRepo;
	
	@Override
	public List<DevoteeHistory> list()
	{
		return (List<DevoteeHistory>) devoteeHistoryRepo.findAll();
	}
	
	@Override
	public DevoteeHistory getById(long devoteeHistoryId)
	{
		return devoteeHistoryRepo.findOne(devoteeHistoryId);
	}
	
	@Override
	public List<DevoteeHistory> getByRatedDevoteeId(long ratedDevoteeId)
	{
		return devoteeHistoryRepo.findByRatedDevotee_id(ratedDevoteeId);
	}
	
	@Override
	public List<DevoteeHistory> getByCommentedByDevoteeId(long commentedByDevoteeId)
	{
		return devoteeHistoryRepo.findByCommentedByDevotee_id(commentedByDevoteeId);
	}
	
	@Override
	public void create(DevoteeHistory devoteeHistory)
	{
		devoteeHistoryRepo.save(devoteeHistory);
	}
	
	@Override
	public void update(DevoteeHistory devoteeHistory)
	{
		devoteeHistoryRepo.save(devoteeHistory);
	}
	
	@Override
	public void delete(long devoteeHistoryId)
	{
		devoteeHistoryRepo.delete(devoteeHistoryId);
	}
}
