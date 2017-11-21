package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followupvolunteer.FollowUpVolunteerDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followupvolunteer.FollowUpVolunteerDetailResponseEntity;

public class FollowUpVolunteerDetailMapper {
	public static FollowUpVolunteerDetailResponseEntity convertToFollowUpVolunteerDetailResponseEntity(FollowUpVolunteer followUpVolunteer) {
		FollowUpVolunteerDetailResponseEntity responseData = new FollowUpVolunteerDetailResponseEntity();
		responseData.setId(followUpVolunteer.getId());
		if (followUpVolunteer.getProgram()!= null) {
			responseData.setProgramId(followUpVolunteer.getProgram().getId());
			responseData.setProgramName(followUpVolunteer.getProgram().getName());
		}
		responseData.setDevoteeId(followUpVolunteer.getDevotee().getId());
		responseData.setDevoteeName(
				(followUpVolunteer.getDevotee().getInitiatedName()!=null)?followUpVolunteer.getDevotee().getInitiatedName():followUpVolunteer.getDevotee().getLegalName()
		);
		return responseData;
	}

	public static void patchFollowUpVolunteer(FollowUpVolunteer followUpVolunteer, FollowUpVolunteerDetailRequestEntity requestData) {
		if (requestData.getId() != null)
			followUpVolunteer.setId(requestData.getId());
		if (requestData.getProgramId() != null) {
			Program program = new Program();
			program.setId(requestData.getProgramId());
			followUpVolunteer.setProgram(program);
		}
		if (requestData.getDevoteeId() != null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getDevoteeId());
		}
	}

	public static Paging setPagingParameters(Page<FollowUpVolunteer> followUpVolunteerPage) {
		 Paging paging = new Paging();
		 paging.setFirst(followUpVolunteerPage.isFirst());
		 paging.setLast(followUpVolunteerPage.isLast());
		 paging.setNumberOfElements(followUpVolunteerPage.getNumberOfElements());
		 paging.setPageNumber(followUpVolunteerPage.getNumber());
		 paging.setPageSize(followUpVolunteerPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(followUpVolunteerPage.getSort()!= null)
			 paging.setSortedOrder(followUpVolunteerPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(followUpVolunteerPage.getTotalElements());
		 paging.setTotalPages(followUpVolunteerPage.getTotalPages());
		 return paging;
	 }
}
