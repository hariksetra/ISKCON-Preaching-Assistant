package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.repo.DevoteeRepo;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.util.CollectionUtils;

@Service
public class DevoteeServiceImpl implements DevoteeService {
	
	@Resource
	private DevoteeRepo devoteeRepo;

	@Override
	public List<Devotee> list() {
		return (List<Devotee>) CollectionUtils.makeCollection(devoteeRepo.findAll());
	}
	
	@Override
	public Page<Devotee> list(Pageable pageable) {
		return devoteeRepo.findAll(pageable);
	}

	@Override
	public Devotee get(long devoteeId) {
		return devoteeRepo.findOne(devoteeId);
	}
	
	@Override
	public Devotee getByEmail(String email)
	{
		return devoteeRepo.findByEmail(email);
	}
	@Override
	public void create(Devotee devotee) {
		devoteeRepo.save(devotee);
	}

	@Override
	public void update(Devotee devotee) {
		devoteeRepo.save(devotee);
	}

	@Override
	public void delete(long devoteeId) {
		devoteeRepo.delete(devoteeId);
	}
	
	@Override
	public List<Devotee> getMyCapturedList(long devoteeId) {
		return (List<Devotee>) CollectionUtils.makeCollection(devoteeRepo.findByCapturedBy(devoteeId));
	}
	
	@Override
	public Page<Devotee> getMyCapturedList(long devoteeId, Pageable pageable) {
		return devoteeRepo.findByCapturedBy(devoteeId, pageable);
	}
	
	@Override
	public Page<Devotee> getAttendeesByProgram(long programId, Pageable pageable){
		return devoteeRepo.findByAttendingPrograms_Program_id(programId, pageable);
	}
	
	@Override
	public Page<Devotee> searchDevotees(String query, Pageable pageable) {
		return devoteeRepo.findByQuery(query, pageable);
	}
	
	@Override
	public String getDisplayName(Devotee devotee) {
		if (devotee.getInitiatedName() != null && devotee.getInitiatedName() != "") {
			return devotee.getInitiatedName();
		} else {
			return devotee.getLegalName();
		}
	}
	
}
