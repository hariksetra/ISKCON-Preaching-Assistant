package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.giridhari.preachingassistant.db.model.ImportantDate;

public interface ImportantDateRepo extends PagingAndSortingRepository<ImportantDate, Long> {

}
