package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ImportantDate;
import com.giridhari.preachingassistant.db.repo.ImportantDateRepo;
import com.giridhari.preachingassistant.service.ImportantDateService;

@Service
public class ImportantDateServiceImpl implements ImportantDateService {

	@Resource
	ImportantDateRepo importantDateRepo;
	
	@Override
	public Page<ImportantDate> list(Pageable pageable) {
		return importantDateRepo.findAll(pageable);
	}

	@Override
	public ImportantDate get(long id) {
		return importantDateRepo.findOne(id);
	}

	@Override
	public void update(ImportantDate importantDate) {
		importantDateRepo.save(importantDate);

	}

	@Override
	public void delete(long id) {
		importantDateRepo.delete(id);
	}

}
