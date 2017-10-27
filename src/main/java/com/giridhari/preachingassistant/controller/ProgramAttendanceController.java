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

import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.mapper.ProgramAttendanceDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.ProgramAttendanceService;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class ProgramAttendanceController {
	@Resource
	ProgramAttendanceService programAttendanceService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramService programService;

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
		if (requestData.getDevoteeId()!=null) programAttendance.setDevoteeId(devoteeService.get(requestData.getDevoteeId()));
		if (requestData.getProgramId()!=null) programAttendance.setProgramId(programService.get(requestData.getProgramId()));
		programAttendanceService.update(programAttendance);
		ProgramAttendanceDetailResponseEntity responseData = ProgramAttendanceDetailMapper.convertToProgramAttendanceDetailResponseEntity(programAttendance);
		return responseData;
	}

	@RequestMapping(name="programAttendanceCreate", value="/programAttendance", method=RequestMethod.POST)
	public ProgramAttendanceDetailResponseEntity post(@RequestBody ProgramAttendanceDetailRequestEntity requestData) {
		ProgramAttendance programAttendance = new ProgramAttendance();
		ProgramAttendanceDetailMapper.patchProgramAttendance(programAttendance, requestData);
		if (requestData.getDevoteeId()!=null) programAttendance.setDevoteeId(devoteeService.get(requestData.getDevoteeId()));
		if (requestData.getProgramId()!=null) programAttendance.setProgramId(programService.get(requestData.getProgramId()));
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