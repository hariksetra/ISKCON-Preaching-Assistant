package com.giridhari.preachingassistant.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.repo.FollowUpAssignmentRepo;
import com.giridhari.preachingassistant.service.FollowUpAssignmentService;

@Service
public class FollowUpAssignmentServiceImpl implements FollowUpAssignmentService {

	@Resource
	FollowUpAssignmentRepo followupAssignmentRepo;
	
	@Override
	public Page<FollowUpAssignment> list(Pageable pageable) {
		return followupAssignmentRepo.findAll(pageable);
	}

	@Override
	public FollowUpAssignment get(long assignmentId) {
		return followupAssignmentRepo.findOne(assignmentId);
	}

	@Override
	public void update(FollowUpAssignment followUpAssignment) {
		followupAssignmentRepo.save(followUpAssignment);

	}

	@Override
	public void delete(long assignmentId) {
		followupAssignmentRepo.delete(assignmentId);
	}

}
