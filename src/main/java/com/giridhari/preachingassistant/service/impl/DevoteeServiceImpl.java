package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

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
	public Devotee get(long devoteeId) {
		return devoteeRepo.findOne(devoteeId);
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
	
}
