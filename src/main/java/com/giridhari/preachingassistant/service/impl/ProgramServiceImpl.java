package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.repo.ProgramRepo;
import com.giridhari.preachingassistant.service.ProgramService;

@Service
public class ProgramServiceImpl implements ProgramService{
	
	@Resource
	ProgramRepo programRepo;
	
	public Page<Program> list(Pageable pageable)
	{
		return programRepo.findAll(pageable);
	}
	
}
