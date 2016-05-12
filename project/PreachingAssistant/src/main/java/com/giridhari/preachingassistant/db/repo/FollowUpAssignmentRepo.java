package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.Repository;

import com.giridhari.preachingassistant.db.model.FollowUpAssignment;

@org.springframework.stereotype.Repository
public interface FollowUpAssignmentRepo extends Repository<FollowUpAssignment, Long> {
	
}