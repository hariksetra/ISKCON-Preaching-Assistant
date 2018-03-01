package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.Devotee;

@Repository
public interface CaptureContactRepo extends PagingAndSortingRepository<CaptureContact, Long> {
	Page<CaptureContact> findByCapturedBy_id(long devotee_id, Pageable pageable);
	CaptureContact findByCapturedByAndCapturedDevotee(Devotee capturedBy, Devotee capturedDevotee);
}
