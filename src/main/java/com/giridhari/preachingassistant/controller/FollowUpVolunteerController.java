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

import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.mapper.FollowUpVolunteerDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followupvolunteer.FollowUpVolunteerDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followupvolunteer.FollowUpVolunteerDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.FollowUpVolunteerService;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class FollowUpVolunteerController {
	@Resource
	FollowUpVolunteerService followUpVolunteerService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;

	@RequestMapping(name="followUpVolunteerPage", value = "/followUpVolunteerPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<FollowUpVolunteer> followUpVolunteerPage = followUpVolunteerService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpVolunteerDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpVolunteerDetailMapper.setPagingParameters(followUpVolunteerPage);
		response.setPaging(paging);
		
		List<FollowUpVolunteer> followUpVolunteerList = followUpVolunteerPage.getContent();
		for(FollowUpVolunteer followUpVolunteer : followUpVolunteerList)
		{
			FollowUpVolunteerDetailResponseEntity followUpVolunteerDetailResponseEntity = FollowUpVolunteerDetailMapper.convertToFollowUpVolunteerDetailResponseEntity(followUpVolunteer);
			responseData.add(followUpVolunteerDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="followUpVolunteerByProgramPage", value = "/followUpVolunteerByProgramPage/{programId}", method = RequestMethod.GET)
	public BaseListResponse listByProgram(@PathVariable("programId") long programId, Pageable pageable)
	{
		Page<FollowUpVolunteer> followUpVolunteerPage = followUpVolunteerService.findByProgram(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpVolunteerDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpVolunteerDetailMapper.setPagingParameters(followUpVolunteerPage);
		response.setPaging(paging);
		
		List<FollowUpVolunteer> followUpVolunteerList = followUpVolunteerPage.getContent();
		for(FollowUpVolunteer followUpVolunteer : followUpVolunteerList)
		{
			FollowUpVolunteerDetailResponseEntity followUpVolunteerDetailResponseEntity = FollowUpVolunteerDetailMapper.convertToFollowUpVolunteerDetailResponseEntity(followUpVolunteer);
			responseData.add(followUpVolunteerDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	//This end point may not be used
	@RequestMapping(name = "followUpVolunteerDetail", value="/followUpVolunteer/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long followUpVolunteerId) {
		FollowUpVolunteer followUpVolunteer = followUpVolunteerService.get(followUpVolunteerId);
		FollowUpVolunteerDetailResponseEntity responseData = FollowUpVolunteerDetailMapper.convertToFollowUpVolunteerDetailResponseEntity(followUpVolunteer);
		return new BaseDataResponse(responseData);
	}

	//This end point may not be used
	@RequestMapping(name = "followUpVolunteerUpdate", value="/followUpVolunteer/{id}", method = RequestMethod.PUT)
	public FollowUpVolunteerDetailResponseEntity put(@PathVariable("id") long followUpVolunteerId, @RequestBody FollowUpVolunteerDetailRequestEntity requestData) {
		FollowUpVolunteer followUpVolunteer = followUpVolunteerService.get(followUpVolunteerId);
		FollowUpVolunteerDetailMapper.patchFollowUpVolunteer(followUpVolunteer, requestData);
		if (requestData.getDevoteeId()!=null) followUpVolunteer.setDevotee(devoteeService.get(requestData.getDevoteeId()));
		if (requestData.getProgramId()!=null) followUpVolunteer.setProgram(programService.get(requestData.getProgramId()));
		followUpVolunteerService.update(followUpVolunteer);
		FollowUpVolunteerDetailResponseEntity responseData = FollowUpVolunteerDetailMapper.convertToFollowUpVolunteerDetailResponseEntity(followUpVolunteer);
		return responseData;
	}

	@RequestMapping(name="followUpVolunteerCreate", value="/followUpVolunteer", method=RequestMethod.POST)
	public BaseListResponse post(@RequestBody FollowUpVolunteerDetailRequestEntity requestData, Pageable pageable) {
		long programId = requestData.getProgramId();
		FollowUpVolunteer followUpVolunteer = new FollowUpVolunteer();
		FollowUpVolunteerDetailMapper.patchFollowUpVolunteer(followUpVolunteer, requestData);
		if (requestData.getDevoteeId()!=null) followUpVolunteer.setDevotee(devoteeService.get(requestData.getDevoteeId()));
		if (requestData.getProgramId()!=null) followUpVolunteer.setProgram(programService.get(requestData.getProgramId()));
		followUpVolunteerService.update(followUpVolunteer);
		
		//Copied from list by program optimize later
		Page<FollowUpVolunteer> followUpVolunteerPage = followUpVolunteerService.findByProgram(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpVolunteerDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpVolunteerDetailMapper.setPagingParameters(followUpVolunteerPage);
		response.setPaging(paging);
		
		List<FollowUpVolunteer> followUpVolunteerList = followUpVolunteerPage.getContent();
		for(FollowUpVolunteer followUpVolunteerItem : followUpVolunteerList)
		{
			FollowUpVolunteerDetailResponseEntity followUpVolunteerDetailResponseEntity = FollowUpVolunteerDetailMapper.convertToFollowUpVolunteerDetailResponseEntity(followUpVolunteerItem);
			responseData.add(followUpVolunteerDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name="followUpVolunteerDelete", value="/followUpVolunteer/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long followUpVolunteerId)
	{
		followUpVolunteerService.delete(followUpVolunteerId);
	}
	
	@RequestMapping(name="followUpVolunteerDeleteAndReturnList", value="/followUpVolunteer/{programId}/{id}", method=RequestMethod.DELETE)
	public BaseListResponse delete(@PathVariable("programId") long programId, @PathVariable("id") long assignmentId, Pageable pageable)
	{
		followUpVolunteerService.delete(assignmentId);
		
		//Copied from list by program optimize later
		Page<FollowUpVolunteer> followUpVolunteerPage = followUpVolunteerService.findByProgram(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpVolunteerDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpVolunteerDetailMapper.setPagingParameters(followUpVolunteerPage);
		response.setPaging(paging);
		
		List<FollowUpVolunteer> followUpVolunteerList = followUpVolunteerPage.getContent();
		for(FollowUpVolunteer followUpVolunteer : followUpVolunteerList)
		{
			FollowUpVolunteerDetailResponseEntity followUpVolunteerDetailResponseEntity = FollowUpVolunteerDetailMapper.convertToFollowUpVolunteerDetailResponseEntity(followUpVolunteer);
			responseData.add(followUpVolunteerDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
}