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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.db.model.mapper.ProgramAssignmentDetailMapper;
import com.giridhari.preachingassistant.db.model.mapper.DevoteeMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;
import com.giridhari.preachingassistant.rest.model.programassignment.ProgramAssignmentDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programassignment.ProgramAssignmentDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.ProgramAssignmentService;
import com.giridhari.preachingassistant.service.ProgramService;
import com.giridhari.preachingassistant.service.YatraService;
import com.giridhari.preachingassistant.util.BadRequestException;
import com.giridhari.preachingassistant.util.NotFoundException;

@RestController
public class ProgramAssignmentController {
	@Resource
	ProgramAssignmentService programAssignmentService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;
	
	@Resource
	YatraService yatraService;

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
	
	@RequestMapping(name="programAssignmentByProgramPage", value = "/programAssignmentByProgramPage/{programId}", method = RequestMethod.GET)
	public BaseListResponse listByProgram(@PathVariable("programId") long programId, Pageable pageable)
	{
		Page<ProgramAssignment> programAssignmentPage = programAssignmentService.findByProgram(programId, pageable);
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
	
	@RequestMapping(name="programAssignmentByProgramList", value = "/programAssignmentByProgramList/{programId}", method = RequestMethod.GET)
	public BaseListResponse listByProgram(@PathVariable("programId") long programId)
	{
		List<ProgramAssignment> programAssignmentList = programAssignmentService.findByProgram(programService.get(programId));
		BaseListResponse response = new BaseListResponse();
		List<ProgramAssignmentDetailResponseEntity> responseData = new ArrayList<>();
				
		for(ProgramAssignment programAssignment : programAssignmentList)
		{
			ProgramAssignmentDetailResponseEntity programAssignmentDetailResponseEntity = ProgramAssignmentDetailMapper.convertToProgramAssignmentDetailResponseEntity(programAssignment);
			responseData.add(programAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="devoteeSearchTypeAheadByProgramPage", value = "/devoteeSearchTypeAheadByProgramPage/{typeText}/{programId}", method = RequestMethod.GET)
	public BaseListResponse typeAheadByProgram(@PathVariable("typeText") String typeText, @PathVariable("programId") long programId, Pageable pageable)
	{
		Page<ProgramAssignment> programAssignmentPage = programAssignmentService.findProgramAssignmentTypeAheadByProgram(typeText, programService.get(programId), pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAssignmentDetailMapper.setPagingParameters(programAssignmentPage);
		response.setPaging(paging);
		
		List<ProgramAssignment> programAssignmentList = programAssignmentPage.getContent();
		for(ProgramAssignment programAssignment : programAssignmentList)
		{
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(programAssignment.getAttendee());
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="devoteeSearchTypeAheadByYatraPage", value = "/devoteeSearchTypeAheadByYatraPage/{typeText}/{yatraId}", method = RequestMethod.GET)
	public BaseListResponse typeAheadByYatra(@PathVariable("typeText") String typeText, @PathVariable("yatraId") long yatraId, Pageable pageable)
	{
		Page<ProgramAssignment> programAssignmentPage = programAssignmentService.findProgramAssignmentTypeAheadByYatra(typeText, yatraService.getById(yatraId), pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAssignmentDetailMapper.setPagingParameters(programAssignmentPage);
		response.setPaging(paging);
		
		List<ProgramAssignment> programAssignmentList = programAssignmentPage.getContent();
		for(ProgramAssignment programAssignment : programAssignmentList)
		{
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(programAssignment.getAttendee());
			responseData.add(devoteeOverviewEntity);
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

	@ResponseBody
	@RequestMapping(name="programAssignmentCreate", value="/programAssignment", method=RequestMethod.POST)
	public BaseListResponse post(@RequestBody ProgramAssignmentDetailRequestEntity requestData, Pageable pageable)  {
		if (requestData.getAttendeeId()==null || requestData.getProgramId()==null) {	
			throw new BadRequestException("Participant/Program details not mentioned");
		}
		
		if (programAssignmentService.findByAttendeeAndProgram(requestData.getAttendeeId(), requestData.getProgramId()) != null) {
			throw new BadRequestException("Participant already added to program");
		}
		
		ProgramAssignment programAssignment = new ProgramAssignment();
		ProgramAssignmentDetailMapper.patchProgramAssignment(programAssignment, requestData);
		if (requestData.getAttendeeId()!=null) programAssignment.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if (requestData.getProgramId()!=null) programAssignment.setProgram(programService.get(requestData.getProgramId()));
		programAssignmentService.update(programAssignment);
	
		long programId = requestData.getProgramId();
		
		Page<ProgramAssignment> programAssignmentPage = programAssignmentService.findByProgram(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAssignmentDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAssignmentDetailMapper.setPagingParameters(programAssignmentPage);
		response.setPaging(paging);
		
		List<ProgramAssignment> programAssignmentList = programAssignmentPage.getContent();
		for(ProgramAssignment programAssignmentData : programAssignmentList)
		{
			ProgramAssignmentDetailResponseEntity programAssignmentDetailResponseEntity = ProgramAssignmentDetailMapper.convertToProgramAssignmentDetailResponseEntity(programAssignmentData);
			responseData.add(programAssignmentDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name="programAssignmentDelete", value="/programAssignment/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long programAssignmentId)
	{
		programAssignmentService.delete(programAssignmentId);
	}
	
	@RequestMapping(name="programAssignmentDeleteAndReturn", value="/programAssignment/{programId}/{id}", method=RequestMethod.DELETE)
	public BaseListResponse delete(@PathVariable("programId") long programId, @PathVariable("id") long programAssignmentId, Pageable pageable)
	{
		programAssignmentService.delete(programAssignmentId);
		
		Page<ProgramAssignment> programAssignmentPage = programAssignmentService.findByProgram(programId, pageable);
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
}