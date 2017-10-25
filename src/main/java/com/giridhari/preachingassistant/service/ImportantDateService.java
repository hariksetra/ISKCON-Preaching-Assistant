package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ImportantDate;

@Service
public interface ImportantDateService {

	public Page<ImportantDate> list(Pageable pageable);
	
	public ImportantDate get(long id);
	
	public void update(ImportantDate importantDate);
	
	public void delete(long id);
}
