package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followupassignment.FollowUpAssignmentDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followupassignment.FollowUpAssignmentDetailResponseEntity;

public class FollowUpAssignmentDetailMapper {
	public static FollowUpAssignmentDetailResponseEntity convertToFollowUpAssignmentDetailResponseEntity(FollowUpAssignment followUpAssignment) {
		FollowUpAssignmentDetailResponseEntity responseData = new FollowUpAssignmentDetailResponseEntity();
		responseData.setId(followUpAssignment.getId());
		if (followUpAssignment.getVolunteer() != null)
			responseData.setVolunteerId(followUpAssignment.getVolunteer().getId());
		if (followUpAssignment.getAttendee() != null)
			responseData.setAttendeeId(followUpAssignment.getAttendee().getId());
		if (followUpAssignment.getProgram() != null)
			responseData.setProgramId(followUpAssignment.getProgram().getId());
		return responseData;
	}

	public static void patchFollowUpAssignment(FollowUpAssignment followUpAssignment, FollowUpAssignmentDetailRequestEntity requestData) {
		if (requestData.getId() != null)
			followUpAssignment.setId(requestData.getId());
		if (requestData.getVolunteerId() != null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getVolunteerId());
			followUpAssignment.setVolunteer(devotee);
		}
		if (requestData.getAttendeeId() != null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getAttendeeId());
			followUpAssignment.setAttendee(devotee);
		}
		if (requestData.getProgramId() != null) {
			Program program =  new Program();
			program.setId(requestData.getProgramId());
			followUpAssignment.setProgram(program);
		}
	}

	public static Paging setPagingParameters(Page<FollowUpAssignment> followUpAssignmentPage) {
		 Paging paging = new Paging();
		 paging.setFirst(followUpAssignmentPage.isFirst());
		 paging.setLast(followUpAssignmentPage.isLast());
		 paging.setNumberOfElements(followUpAssignmentPage.getNumberOfElements());
		 paging.setPageNumber(followUpAssignmentPage.getNumber());
		 paging.setPageSize(followUpAssignmentPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(followUpAssignmentPage.getSort()!= null)
			 paging.setSortedOrder(followUpAssignmentPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(followUpAssignmentPage.getTotalElements());
		 paging.setTotalPages(followUpAssignmentPage.getTotalPages());
		 return paging;
	 }
}
