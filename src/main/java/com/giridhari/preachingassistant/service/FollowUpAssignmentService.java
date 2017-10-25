package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUpAssignment;

@Service
public interface FollowUpAssignmentService {

	public Page<FollowUpAssignment> list(Pageable pageable);
	
	public FollowUpAssignment get(long assignmentId);
	
	public void update(FollowUpAssignment followUpAssignment);
	
	public void delete(long assignmentId);
}
