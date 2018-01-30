package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.model.mapper.FollowUpDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followup.FollowUpDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followup.FollowUpDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.FollowUpService;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class FollowUpController {
	@Resource
	FollowUpService followUpService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;

	@RequestMapping(name="followUpPage", value = "/followUpPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<FollowUp> followUpPage = followUpService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpDetailMapper.setPagingParameters(followUpPage);
		response.setPaging(paging);
		
		List<FollowUp> followUpList = followUpPage.getContent();
		for(FollowUp followUp : followUpList)
		{
			FollowUpDetailResponseEntity followUpDetailResponseEntity = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
			responseData.add(followUpDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "followUpDetail", value="/followUp/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long followUpId) {
		FollowUp followUp = followUpService.get(followUpId);
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		return new BaseDataResponse(responseData);
	}
	
	@RequestMapping(name = "followUpDetailSpecific", value="/specificFollowUpRecord/{programId}/{attendeeId}/{volunteerId}", method = RequestMethod.GET)
	public BaseDataResponse getFollowUpRecord(@PathVariable("programId") long programId, @PathVariable("attendeeId") long attendeeId, @PathVariable("volunteerId") long volunteerId) {
		FollowUp followUp = followUpService.getFollowUpRecord(programService.get(programId), devoteeService.get(attendeeId), devoteeService.get(volunteerId));
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "followUpUpdate", value="/followUp/{id}", method = RequestMethod.PUT)
	public FollowUpDetailResponseEntity put(@PathVariable("id") long followUpId, @RequestBody FollowUpDetailRequestEntity requestData) {
		FollowUp followUp = followUpService.get(followUpId);
		FollowUpDetailMapper.patchFollowUp(followUp, requestData);
		if(requestData.getAttendeeId()!=null) followUp.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if(requestData.getVolunteerId()!=null) followUp.setVolunteer(devoteeService.get(requestData.getVolunteerId()));
		if(requestData.getProgramId()!=null) followUp.setProgram(programService.get(requestData.getProgramId()));
		followUpService.update(followUp);
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		return responseData;
	}

	@RequestMapping(name="followUpCreate", value="/followUp", method=RequestMethod.POST)
	public FollowUpDetailResponseEntity post(@RequestBody FollowUpDetailRequestEntity requestData) {
		FollowUp followUp = new FollowUp();
		FollowUpDetailMapper.patchFollowUp(followUp, requestData);
		if(requestData.getAttendeeId()!=null) followUp.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if(requestData.getVolunteerId()!=null) followUp.setVolunteer(devoteeService.get(requestData.getVolunteerId()));
		if(requestData.getProgramId()!=null) followUp.setProgram(programService.get(requestData.getProgramId()));
		followUpService.update(followUp);
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		return responseData;
	}

	@RequestMapping(name="followUpDelete", value="/followUp/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long followUpId)
	{
		followUpService.delete(followUpId);
	}
	
	@RequestMapping(name="followUpDelete", value="/deleteFollowUpOfProgram/{programId}", method=RequestMethod.DELETE)
	public void clearFollowupOfProgram(@PathVariable("programId") long programId)
	{
		followUpService.clearFollowupOfProgram(programService.get(programId));
	}
}