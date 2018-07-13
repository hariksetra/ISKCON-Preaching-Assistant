package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followup.FollowUpDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followup.FollowUpDetailResponseEntity;

public class FollowUpDetailMapper {
	public static FollowUpDetailResponseEntity convertToFollowUpDetailResponseEntity(FollowUp followUp) {
		FollowUpDetailResponseEntity responseData = new FollowUpDetailResponseEntity();
		responseData.setId(followUp.getId());
		if (followUp.getVolunteer() != null) {
			responseData.setVolunteerId(followUp.getVolunteer().getId());
			responseData.setVolunteerName(followUp.getVolunteer().getLegalName());
		}
		
		if (followUp.getAttendee() != null) {
			responseData.setAttendeeId(followUp.getAttendee().getId());
			responseData.setAttendeeName(followUp.getAttendee().getLegalName());
		}
		if (followUp.getProgram()!=null) {
			responseData.setProgramId(followUp.getProgram().getId());
			responseData.setProgramName(followUp.getProgram().getName());
		}
		responseData.setResponse(followUp.getResponse());
		responseData.setComment(followUp.getComment());
		responseData.setRating(followUp.getRating());
		responseData.setTimestamp(followUp.getTimestamp());
		responseData.setTaskStatus(followUp.getTaskStatus());
		return responseData;
	}

	public static void patchFollowUp(FollowUp followUp, FollowUpDetailRequestEntity requestData) {
		if (requestData.getId() != null)
			followUp.setId(requestData.getId());
		if (requestData.getVolunteerId() != null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getVolunteerId());
			followUp.setVolunteer(devotee);
		}
		if (requestData.getAttendeeId() != null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getAttendeeId());
			followUp.setAttendee(devotee);
		}
		if (requestData.getProgramId()!=null) {
			Program program = new Program();
			program.setId(requestData.getProgramId());
			followUp.setProgram(program);
		}
		if (requestData.getResponse() != null)
			followUp.setResponse(requestData.getResponse());
		if (requestData.getComment() != null)
			followUp.setComment(requestData.getComment());
		if (requestData.getRating() != null)
			followUp.setRating(requestData.getRating());
		if (requestData.getTimestamp() != null)
			followUp.setTimestamp(requestData.getTimestamp());
		if (requestData.getTaskStatus() != null)
			followUp.setTaskStatus(requestData.getTaskStatus());
	}

	public static Paging setPagingParameters(Page<FollowUp> followUpPage) {
		 Paging paging = new Paging();
		 paging.setFirst(followUpPage.isFirst());
		 paging.setLast(followUpPage.isLast());
		 paging.setNumberOfElements(followUpPage.getNumberOfElements());
		 paging.setPageNumber(followUpPage.getNumber());
		 paging.setPageSize(followUpPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(followUpPage.getSort()!= null)
			 paging.setSortedOrder(followUpPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(followUpPage.getTotalElements());
		 paging.setTotalPages(followUpPage.getTotalPages());
		 return paging;
	 }

}
