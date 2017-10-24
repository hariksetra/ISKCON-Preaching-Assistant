package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.program.ProgramDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.program.ProgramDetailResponseEntity;

public class ProgramMapper {
	public static ProgramDetailResponseEntity convertToProgramDetailResponseEntity(Program program)
	{
		ProgramDetailResponseEntity responseData = new ProgramDetailResponseEntity();
		responseData.setAddress(program.getAddress());
		responseData.setDescription(program.getDescription());
		if (program.getFollowupDescription() != null) {
			responseData.setFollowupDescription(program.getFollowupDescription().split("~"));
		}
		responseData.setId(program.getId());
		responseData.setMapLocation(program.getMapLocation());
		if(program.getMentor() != null)
			responseData.setMentorId(program.getMentor().getId());
		responseData.setName(program.getName());
		if(program.getParentYatra() != null)
			responseData.setParentYatraId(program.getParentYatra().getId());
		responseData.setTargetAudience(program.getTargetAudience());
		responseData.setType(program.getType());
		return responseData;
	}
	
	public static void patchProgram(Program program, ProgramDetailRequestEntity requestData)
	{
		if(requestData.getAddress() != null)
			program.setAddress(requestData.getAddress());
		if(requestData.getDescription() != null)
			program.setDescription(requestData.getDescription());
		if(requestData.getFollowupDescription() != null) {
			program.setFollowupDescription(String.join("~", requestData.getFollowupDescription()));
		}
		if(requestData.getMapLocation() != null)
			program.setMapLocation(requestData.getMapLocation());
		if(requestData.getMentorId() != null)
		{
			Devotee mentor = new Devotee();
			mentor.setId(requestData.getMentorId());
			program.setMentor(mentor);
		}
		if(requestData.getName() != null)
			program.setName(requestData.getName());
		if(requestData.getParentYatraId() != null)
		{
			Yatra yatra = new Yatra();
			yatra.setId(requestData.getId());
			program.setParentYatra(yatra);
		}
		if(requestData.getTargetAudience() != null)
			program.setTargetAudience(requestData.getTargetAudience());
		if(requestData.getType() != null)
			program.setType(requestData.getType());
	}
	
	public static Paging setPagingParameters(Page<Program> programPage)
	 {
		 Paging paging = new Paging();
		 paging.setFirst(programPage.isFirst());
		 paging.setLast(programPage.isLast());
		 paging.setNumberOfElements(programPage.getNumberOfElements());
		 paging.setPageNumber(programPage.getNumber());
		 paging.setPageSize(programPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(programPage.getSort()!= null)
			 paging.setSortedOrder(programPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(programPage.getTotalElements());
		 paging.setTotalPages(programPage.getTotalPages());
		 return paging;
	 }
}
