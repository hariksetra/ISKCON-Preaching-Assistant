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

import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.db.model.mapper.ProgramSessionDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programsession.ProgramSessionDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programsession.ProgramSessionDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.ProgramService;
import com.giridhari.preachingassistant.service.ProgramSessionService;

@RestController
public class ProgramSessionController {
	@Resource
	ProgramSessionService programSessionService;
	
	@Resource
	ProgramService programService;

	@RequestMapping(name="programSessionPage", value = "/programSessionPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<ProgramSession> programSessionPage = programSessionService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramSessionDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramSessionDetailMapper.setPagingParameters(programSessionPage);
		response.setPaging(paging);
		
		List<ProgramSession> programSessionList = programSessionPage.getContent();
		for(ProgramSession programSession : programSessionList)
		{
			ProgramSessionDetailResponseEntity programSessionDetailResponseEntity = ProgramSessionDetailMapper.convertToProgramSessionDetailResponseEntity(programSession);
			responseData.add(programSessionDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="programSessionByProgramPage", value = "/programSessionByProgramPage/{programId}", method = RequestMethod.GET)
	public BaseListResponse programSessionByProgramPage(@PathVariable("programId") long programId, Pageable pageable)
	{
		Page<ProgramSession> programSessionPage = programSessionService.findByProgram(programService.get(programId), pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramSessionDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramSessionDetailMapper.setPagingParameters(programSessionPage);
		response.setPaging(paging);
		
		List<ProgramSession> programSessionList = programSessionPage.getContent();
		for(ProgramSession programSession : programSessionList)
		{
			ProgramSessionDetailResponseEntity programSessionDetailResponseEntity = ProgramSessionDetailMapper.convertToProgramSessionDetailResponseEntity(programSession);
			responseData.add(programSessionDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name = "programSessionOfProgramByDate", value="/programSessionOfProgramByDate/{programId}/{sessionDate}", method = RequestMethod.GET)
	public BaseDataResponse programSessionOfProgramByDate(@PathVariable("programId") long programId, @PathVariable("sessionDate") Date sessionDate) {
		ProgramSession programSession = programSessionService.findSessionOfProgramByDate(programService.get(programId), sessionDate);
		ProgramSessionDetailResponseEntity responseData = ProgramSessionDetailMapper.convertToProgramSessionDetailResponseEntity(programSession);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "programSessionDetail", value="/programSession/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long programSessionId) {
		ProgramSession programSession = programSessionService.get(programSessionId);
		ProgramSessionDetailResponseEntity responseData = ProgramSessionDetailMapper.convertToProgramSessionDetailResponseEntity(programSession);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "programSessionUpdate", value="/programSession/{id}", method = RequestMethod.PUT)
	public ProgramSessionDetailResponseEntity put(@PathVariable("id") long programSessionId, @RequestBody ProgramSessionDetailRequestEntity requestData) {
		ProgramSession programSession = programSessionService.get(programSessionId);
		ProgramSessionDetailMapper.patchProgramSession(programSession, requestData);
		if(requestData.getProgramId()!=null) programSession.setProgram(programService.get(requestData.getProgramId()));
		programSessionService.update(programSession);
		ProgramSessionDetailResponseEntity responseData = ProgramSessionDetailMapper.convertToProgramSessionDetailResponseEntity(programSession);
		return responseData;
	}

	@RequestMapping(name="programSessionCreate", value="/programSession", method=RequestMethod.POST)
	public ProgramSessionDetailResponseEntity post(@RequestBody ProgramSessionDetailRequestEntity requestData) {
		ProgramSession programSession = new ProgramSession();
		ProgramSessionDetailMapper.patchProgramSession(programSession, requestData);
		if(requestData.getProgramId()!=null) programSession.setProgram(programService.get(requestData.getProgramId()));
		programSessionService.update(programSession);
		ProgramSessionDetailResponseEntity responseData = ProgramSessionDetailMapper.convertToProgramSessionDetailResponseEntity(programSession);
		return responseData;
	}

	@RequestMapping(name="programSessionDelete", value="/programSession/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long programSessionId)
	{
		programSessionService.delete(programSessionId);
	}
}