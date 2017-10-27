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

import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.db.model.mapper.ProgramAssignmentDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programassignment.ProgramAssignmentDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programassignment.ProgramAssignmentDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.ProgramAssignmentService;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class ProgramAssignmentController {
	@Resource
	ProgramAssignmentService programAssignmentService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;

	@RequestMapping(name="programAssignmentPage", value = "/programAssignmentPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<ProgramAssignment> programAssignmentPage = programAssignmentService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAssignmentDetailMapper.setPagingParameters(programAssignmentPage);
		response.setPaging(paging);
		
		List<ProgramAssignment> programAssignmentList = programAssignmentPage.getContent();
		for(ProgramAssignment programAssignment : programAssignmentList)
		{
			ProgramAssignmentDetailResponseEntity programAssignmentDetailResponseEntity = ProgramAssignmentDetailMapper.convertToProgramAssignmentDetailResponseEntity(programAssignment);
			responseData.add(programAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "programAssignmentDetail", value="/programAssignment/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long programAssignmentId) {
		ProgramAssignment programAssignment = programAssignmentService.get(programAssignmentId);
		ProgramAssignmentDetailResponseEntity responseData = ProgramAssignmentDetailMapper.convertToProgramAssignmentDetailResponseEntity(programAssignment);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "programAssignmentUpdate", value="/programAssignment/{id}", method = RequestMethod.PUT)
	public ProgramAssignmentDetailResponseEntity put(@PathVariable("id") long programAssignmentId, @RequestBody ProgramAssignmentDetailRequestEntity requestData) {
		ProgramAssignment programAssignment = programAssignmentService.get(programAssignmentId);
		ProgramAssignmentDetailMapper.patchProgramAssignment(programAssignment, requestData);
		if (requestData.getAttendeeId()!=null) programAssignment.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if (requestData.getProgramId()!=null) programAssignment.setProgram(programService.get(requestData.getProgramId()));
		programAssignmentService.update(programAssignment);
		ProgramAssignmentDetailResponseEntity responseData = ProgramAssignmentDetailMapper.convertToProgramAssignmentDetailResponseEntity(programAssignment);
		return responseData;
	}

	@RequestMapping(name="programAssignmentCreate", value="/programAssignment", method=RequestMethod.POST)
	public ProgramAssignmentDetailResponseEntity post(@RequestBody ProgramAssignmentDetailRequestEntity requestData) {
		ProgramAssignment programAssignment = new ProgramAssignment();
		ProgramAssignmentDetailMapper.patchProgramAssignment(programAssignment, requestData);
		if (requestData.getAttendeeId()!=null) programAssignment.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if (requestData.getProgramId()!=null) programAssignment.setProgram(programService.get(requestData.getProgramId()));
		programAssignmentService.update(programAssignment);
		ProgramAssignmentDetailResponseEntity responseData = ProgramAssignmentDetailMapper.convertToProgramAssignmentDetailResponseEntity(programAssignment);
		return responseData;
	}

	@RequestMapping(name="programAssignmentDelete", value="/programAssignment/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long programAssignmentId)
	{
		programAssignmentService.delete(programAssignmentId);
	}
}