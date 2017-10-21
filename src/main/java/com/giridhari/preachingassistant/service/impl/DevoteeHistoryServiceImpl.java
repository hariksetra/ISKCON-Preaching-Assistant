package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.repo.DevoteeHistoryRepo;
import com.giridhari.preachingassistant.service.DevoteeHistoryService;
import com.giridhari.preachingassistant.service.DevoteeService;

@Service
public class DevoteeHistoryServiceImpl implements DevoteeHistoryService{

	@Resource
	DevoteeHistoryRepo devoteeHistoryRepo;
	
	@Resource
	DevoteeService devoteeService;
	
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
	public Page<DevoteeHistory> getByRatedDevoteeId(long ratedDevoteeId, Pageable pageable)
	{
		return devoteeHistoryRepo.findByRatedDevotee_id(ratedDevoteeId, pageable);
	}
	
	@Override
	public List<DevoteeHistory> getByCommentedByDevoteeId(long commentedByDevoteeId)
	{
		return devoteeHistoryRepo.findByCommentedByDevotee_id(commentedByDevoteeId);
	}
	
	@Override
	public Page<DevoteeHistory> getByCommentedByDevoteeId(long commentedByDevoteeId, Pageable pageable)
	{
		return devoteeHistoryRepo.findByCommentedByDevotee_id(commentedByDevoteeId, pageable);
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
	
	@Override
	public String getCommentedByDevoteeName(DevoteeHistory devoteeHistory) {
		String commentedByDevoteeName;
		if (devoteeHistory.getCommentedByDevotee() != null) {
			commentedByDevoteeName = devoteeService.getDisplayName(devoteeHistory.getCommentedByDevotee());
		} else commentedByDevoteeName = "";
		return commentedByDevoteeName;
	}
	
	@Override
	public String getRatedByDevoteeName(DevoteeHistory devoteeHistory) {
		String ratedDevoteeName;
		if (devoteeHistory.getRatedDevotee() != null) {
			ratedDevoteeName = devoteeService.getDisplayName(devoteeHistory.getRatedDevotee());
		} else ratedDevoteeName = "";
		return ratedDevoteeName;
	}
	
	@Override
	public Page<DevoteeHistory> listAllByPage(Pageable pageable)
	{
		return devoteeHistoryRepo.findAll(pageable);
	}
}
