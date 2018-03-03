package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.mapper.FollowUpAssignmentDetailMapper;
import com.giridhari.preachingassistant.exception.AssignerNotFoundException;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followupassignment.FollowUpAssignmentDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followupassignment.FollowUpAssignmentDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.FollowUpAssignmentService;
import com.giridhari.preachingassistant.service.FollowUpVolunteerService;
import com.giridhari.preachingassistant.service.ProgramAssignmentService;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class FollowUpAssignmentController {
	@Resource
	FollowUpAssignmentService followUpAssignmentService;
		
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;
	
	@Resource
	ProgramAssignmentService programAssignmentService;
	
	@Resource
	FollowUpVolunteerService followUpVolunteerService;

	@RequestMapping(name="followUpAssignmentPage", value = "/followUpAssignmentPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<FollowUpAssignment> followUpAssignmentPage = followUpAssignmentService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpAssignmentDetailMapper.setPagingParameters(followUpAssignmentPage);
		response.setPaging(paging);
		
		List<FollowUpAssignment> followUpAssignmentList = followUpAssignmentPage.getContent();
		for(FollowUpAssignment followUpAssignment : followUpAssignmentList)
		{
			FollowUpAssignmentDetailResponseEntity followUpAssignmentDetailResponseEntity = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
			responseData.add(followUpAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="followUpAssignmentByVolunteerPage", value = "/followUpAssignmentByVolunteerPage/{volunteerId}", method = RequestMethod.GET)
	public BaseListResponse listByVolunteer(@PathVariable("volunteerId") long volunteerId, Pageable pageable)
	{
		Page<FollowUpAssignment> followUpAssignmentPage = followUpAssignmentService.listByVolunteer(devoteeService.get(volunteerId), pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpAssignmentDetailMapper.setPagingParameters(followUpAssignmentPage);
		response.setPaging(paging);
		
		List<FollowUpAssignment> followUpAssignmentList = followUpAssignmentPage.getContent();
		for(FollowUpAssignment followUpAssignment : followUpAssignmentList)
		{
			FollowUpAssignmentDetailResponseEntity followUpAssignmentDetailResponseEntity = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
			responseData.add(followUpAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="followUpAssignmentAttendeesForVolunteerByProgramPage", value = "/followUpAssignmentAttendeesForVolunteerByProgramPage/{volunteerId}/{programId}", method = RequestMethod.GET)
	public BaseListResponse listOfAttendeesForVolunteerByProgram(@PathVariable("volunteerId") long volunteerId, @PathVariable("programId") long programId, Pageable pageable)
	{
		//TODO: followUpAssignmentService.listByVolunteerAndProgram NOT WORKING
		Page<FollowUpAssignment> followUpAssignmentPage = followUpAssignmentService.listByVolunteerAndProgram(devoteeService.get(volunteerId), programService.get(programId), pageable);
		//Page<FollowUpAssignment> followUpAssignmentPage = followUpAssignmentService.listByVolunteer(devoteeService.get(volunteerId), pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpAssignmentDetailMapper.setPagingParameters(followUpAssignmentPage);
		response.setPaging(paging);
		
		List<FollowUpAssignment> followUpAssignmentList = followUpAssignmentPage.getContent();
		for(FollowUpAssignment followUpAssignment : followUpAssignmentList)
		{
			FollowUpAssignmentDetailResponseEntity followUpAssignmentDetailResponseEntity = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
			responseData.add(followUpAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="followUpAssignmentsByProgramPage", value = "/followUpAssignmentsByProgramPage/{programId}", method = RequestMethod.GET)
	public BaseListResponse listOfFollowupAssignmentsByProgram(@PathVariable("programId") long programId, Pageable pageable)
	{
		Page<FollowUpAssignment> followUpAssignmentPage = followUpAssignmentService.listByProgram(programService.get(programId), pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpAssignmentDetailMapper.setPagingParameters(followUpAssignmentPage);
		response.setPaging(paging);
		
		List<FollowUpAssignment> followUpAssignmentList = followUpAssignmentPage.getContent();
		for(FollowUpAssignment followUpAssignment : followUpAssignmentList)
		{
			FollowUpAssignmentDetailResponseEntity followUpAssignmentDetailResponseEntity = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
			responseData.add(followUpAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="followUpProgramsForVolunteerPage", value = "/followUpProgramsForVolunteerPage/{volunteerId}", method = RequestMethod.GET)
	public BaseListResponse listOfProgramsForVolunteer(@PathVariable("volunteerId") long volunteerId, Pageable pageable)
	{
		List<FollowUpAssignment> listOfDistinctPrograms = followUpAssignmentService.listOfProgramsForVolunteer(devoteeService.get(volunteerId));
		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > listOfDistinctPrograms.size() ? listOfDistinctPrograms.size() : (start + pageable.getPageSize());
		List<FollowUpAssignment> paginatedContent;
		if (end >= start) {
			paginatedContent = listOfDistinctPrograms.subList(start, end);
		} else {
			paginatedContent = new ArrayList<FollowUpAssignment>();
		}
		Page<FollowUpAssignment> listOfProgramsPage = new PageImpl<FollowUpAssignment>(paginatedContent, pageable, listOfDistinctPrograms.size());

		BaseListResponse response = new BaseListResponse();

		List<FollowUpAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpAssignmentDetailMapper.setPagingParameters(listOfProgramsPage);
		response.setPaging(paging);
		
		List<FollowUpAssignment> followUpAssignmentList = listOfProgramsPage.getContent();
		for(FollowUpAssignment followUpAssignment : followUpAssignmentList)
		{
			FollowUpAssignmentDetailResponseEntity followUpAssignmentDetailResponseEntity = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
			responseData.add(followUpAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "followUpAssignmentDetail", value="/followUpAssignment/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long followUpAssignmentId) {
		FollowUpAssignment followUpAssignment = followUpAssignmentService.get(followUpAssignmentId);
		FollowUpAssignmentDetailResponseEntity responseData = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "followUpAssignmentUpdate", value="/followUpAssignment/{id}", method = RequestMethod.PUT)
	public FollowUpAssignmentDetailResponseEntity put(@PathVariable("id") long followUpAssignmentId, @RequestBody FollowUpAssignmentDetailRequestEntity requestData) {
		FollowUpAssignment followUpAssignment = followUpAssignmentService.get(followUpAssignmentId);
		FollowUpAssignmentDetailMapper.patchFollowUpAssignment(followUpAssignment, requestData);
		if (requestData.getAttendeeId()!=null)followUpAssignment.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if (requestData.getVolunteerId()!=null) followUpAssignment.setVolunteer(devoteeService.get(requestData.getVolunteerId()));
		if (requestData.getProgramId()!=null) followUpAssignment.setProgram(programService.get(requestData.getProgramId()));
		followUpAssignmentService.update(followUpAssignment);
		FollowUpAssignmentDetailResponseEntity responseData = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
		return responseData;
	}

	@RequestMapping(name="followUpAssignmentCreate", value="/followUpAssignment", method=RequestMethod.POST)
	public FollowUpAssignmentDetailResponseEntity post(@RequestBody FollowUpAssignmentDetailRequestEntity requestData) {
		FollowUpAssignment followUpAssignment = new FollowUpAssignment();
		FollowUpAssignmentDetailMapper.patchFollowUpAssignment(followUpAssignment, requestData);
		if (requestData.getAttendeeId()!=null)followUpAssignment.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if (requestData.getVolunteerId()!=null) followUpAssignment.setVolunteer(devoteeService.get(requestData.getVolunteerId()));
		if (requestData.getProgramId()!=null) followUpAssignment.setProgram(programService.get(requestData.getProgramId()));
		followUpAssignmentService.update(followUpAssignment);
		FollowUpAssignmentDetailResponseEntity responseData = FollowUpAssignmentDetailMapper.convertToFollowUpAssignmentDetailResponseEntity(followUpAssignment);
		return responseData;
	}

	@RequestMapping(name="followUpAssignmentDelete", value="/followUpAssignment/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long followUpAssignmentId)
	{
		followUpAssignmentService.delete(followUpAssignmentId);
	}
	  
	@Transactional
	@RequestMapping(name="followUpAssignmentDeleteForProgram", value="/followUpAssignmentDeleteForProgram/{programId}", method=RequestMethod.DELETE)
	public void deleteAssignmentsOfProgram(@PathVariable("programId") long programId)
	{
		followUpAssignmentService.deleteAssignmentsOfProgram(programService.get(programId));
	}
	
	@Transactional
	@RequestMapping(name="autoFollowUpAssignment", value="/autoFollowUpAssignment/{programId}", method=RequestMethod.POST)
	public void autoAssign(@PathVariable("programId") long programId) throws AssignerNotFoundException {
		followUpAssignmentService.autoAssign(programService.get(programId), "default");
	}
}