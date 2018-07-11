package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.mapper.DevoteeMapper;
import com.giridhari.preachingassistant.db.model.mapper.ProgramAttendanceDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceId;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceSpecificDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.ProgramAttendanceService;
import com.giridhari.preachingassistant.service.ProgramService;
import com.giridhari.preachingassistant.service.ProgramSessionService;

@RestController
public class ProgramAttendanceController {
	@Resource
	ProgramAttendanceService programAttendanceService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;
	
	@Resource
	ProgramSessionService programSessionService;

	@RequestMapping(name="programAttendancePage", value = "/programAttendancePage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<ProgramAttendance> programAttendancePage = programAttendanceService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAttendanceDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAttendanceDetailMapper.setPagingParameters(programAttendancePage);
		response.setPaging(paging);
		
		List<ProgramAttendance> programAttendanceList = programAttendancePage.getContent();
		for(ProgramAttendance programAttendance : programAttendanceList)
		{
			ProgramAttendanceDetailResponseEntity programAttendanceDetailResponseEntity = ProgramAttendanceDetailMapper.convertToProgramAttendanceDetailResponseEntity(programAttendance);
			responseData.add(programAttendanceDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="programAttendanceBySessionPage", value = "/programAttendanceBySessionPage/{sessionId}", method = RequestMethod.GET)
	public BaseListResponse programAttendanceBySessionPage(@PathVariable("sessionId") long sessionId, Pageable pageable)
	{
		Page<ProgramAttendance> programAttendancePage = programAttendanceService.attendanceBySession(programSessionService.get(sessionId), pageable);
		BaseListResponse response = new BaseListResponse();
		
		//This function responds with one record for all the attendance of the specified program
		//Within that one record there is a list of devotee overview entity, as this can be 
		//readily used by front end
		//That one record also have a list of {attendanceId, devoteeId}, which facilitates
		//easy deletion of a attendance by the front end
		List<ProgramAttendanceSpecificDetailResponseEntity> responseData = new ArrayList<>();
		ProgramAttendanceSpecificDetailResponseEntity responseDataContent = new ProgramAttendanceSpecificDetailResponseEntity(); 
		List<ProgramAttendanceId> attendanceIdRecord = new  ArrayList<>();
		List<DevoteeOverviewEntity> devoteeList = new ArrayList<>();
				
		Paging paging = ProgramAttendanceDetailMapper.setPagingParameters(programAttendancePage);
		response.setPaging(paging);
		
		List<ProgramAttendance> programAttendanceList = programAttendancePage.getContent();
		for(ProgramAttendance programAttendance : programAttendanceList)
		{
			DevoteeOverviewEntity devotee = DevoteeMapper.convertToDevoteeOverviewEntity(programAttendance.getDevotee());
			devoteeList.add(devotee);
			ProgramAttendanceId programAttendanceId = new ProgramAttendanceId();
			programAttendanceId.setAttendanceId(programAttendance.getId());
			programAttendanceId.setDevoteeId(programAttendance.getDevotee().getId());
			attendanceIdRecord.add(programAttendanceId);
		}
		
		responseDataContent.setAttendanceId(attendanceIdRecord);
		responseDataContent.setDevoteeList(devoteeList);
		responseData.add(responseDataContent);
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "programAttendanceDetail", value="/programAttendance/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long programAttendanceId) {
		ProgramAttendance programAttendance = programAttendanceService.get(programAttendanceId);
		ProgramAttendanceDetailResponseEntity responseData = ProgramAttendanceDetailMapper.convertToProgramAttendanceDetailResponseEntity(programAttendance);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "programAttendanceUpdate", value="/programAttendance/{id}", method = RequestMethod.PUT)
	public ProgramAttendanceDetailResponseEntity put(@PathVariable("id") long programAttendanceId, @RequestBody ProgramAttendanceDetailRequestEntity requestData) {
		ProgramAttendance programAttendance = programAttendanceService.get(programAttendanceId);
		ProgramAttendanceDetailMapper.patchProgramAttendance(programAttendance, requestData);
		if (requestData.getDevoteeId()!=null) programAttendance.setDevotee(devoteeService.get(requestData.getDevoteeId()));
		programAttendanceService.update(programAttendance);
		ProgramAttendanceDetailResponseEntity responseData = ProgramAttendanceDetailMapper.convertToProgramAttendanceDetailResponseEntity(programAttendance);
		return responseData;
	}

	@RequestMapping(name="programAttendanceCreate", value="/programAttendance", method=RequestMethod.POST)
	public ProgramAttendanceDetailResponseEntity post(@RequestBody ProgramAttendanceDetailRequestEntity requestData) {
		ProgramAttendance programAttendance = new ProgramAttendance();
		ProgramAttendanceDetailMapper.patchProgramAttendance(programAttendance, requestData);
		if (requestData.getDevoteeId()!=null) programAttendance.setDevotee(devoteeService.get(requestData.getDevoteeId()));
		programAttendanceService.update(programAttendance);
		ProgramAttendanceDetailResponseEntity responseData = ProgramAttendanceDetailMapper.convertToProgramAttendanceDetailResponseEntity(programAttendance);
		return responseData;
	}

	@RequestMapping(name="programAttendanceDelete", value="/programAttendance/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long programAttendanceId)
	{
		programAttendanceService.delete(programAttendanceId);
	}
}