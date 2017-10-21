package com.giridhari.preachingassistant.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.db.repo.YatraRepo;
import com.giridhari.preachingassistant.service.YatraService;
import com.giridhari.preachingassistant.util.CollectionUtils;

@Service
public class YatraServiceImpl implements YatraService {
	
	@Resource
	private YatraRepo yatraRepo;

	@Override
	public List<Yatra> list() {
		return (List<Yatra>) CollectionUtils.makeCollection(yatraRepo.findAll());
	}
	
	@Override
	public Page<Yatra> list(Pageable pageable) {
		return yatraRepo.findAll(pageable);
	}

	@Override
	public Yatra getById(long yatraId) {
		return yatraRepo.findOne(yatraId);
	}

	@Override
	public void create(Yatra yatra) {
		yatraRepo.save(yatra);
	}

	@Override
	public void update(Yatra yatra) {
		yatraRepo.save(yatra);
	}

	@Override
	public void delete(long yatraId) {
		yatraRepo.delete(yatraId);
	}
	
}
