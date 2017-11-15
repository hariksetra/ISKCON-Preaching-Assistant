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

import com.giridhari.preachingassistant.db.model.ProgramAreaSubscription;
import com.giridhari.preachingassistant.db.model.mapper.ProgramAreaSubscriptionDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programareasubscription.ProgramAreaSubscriptionDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programareasubscription.ProgramAreaSubscriptionDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.ProgramAreaSubscriptionService;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class ProgramAreaSubscriptionController {
	@Resource
	ProgramAreaSubscriptionService programAreaSubscriptionService;
	
	@Resource
	ProgramService programService;

	@RequestMapping(name="programAreaSubscriptionPage", value = "/programAreaSubscriptionPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<ProgramAreaSubscription> programAreaSubscriptionPage = programAreaSubscriptionService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAreaSubscriptionDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAreaSubscriptionDetailMapper.setPagingParameters(programAreaSubscriptionPage);
		response.setPaging(paging);
		
		List<ProgramAreaSubscription> programAreaSubscriptionList = programAreaSubscriptionPage.getContent();
		for(ProgramAreaSubscription programAreaSubscription : programAreaSubscriptionList)
		{
			ProgramAreaSubscriptionDetailResponseEntity programAreaSubscriptionDetailResponseEntity = ProgramAreaSubscriptionDetailMapper.convertToProgramAreaSubscriptionDetailResponseEntity(programAreaSubscription);
			responseData.add(programAreaSubscriptionDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name="programAreaSubscriptionByProgramIdPage", value = "/programAreaSubscriptionByProgramPage/{programId}", method = RequestMethod.GET)
	public BaseListResponse list(@PathVariable("programId") long programId, Pageable pageable)
	{
		Page<ProgramAreaSubscription> programAreaSubscriptionPage = programAreaSubscriptionService.findByProgramId(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAreaSubscriptionDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAreaSubscriptionDetailMapper.setPagingParameters(programAreaSubscriptionPage);
		response.setPaging(paging);
		
		List<ProgramAreaSubscription> programAreaSubscriptionList = programAreaSubscriptionPage.getContent();
		for(ProgramAreaSubscription programAreaSubscription : programAreaSubscriptionList)
		{
			ProgramAreaSubscriptionDetailResponseEntity programAreaSubscriptionDetailResponseEntity = ProgramAreaSubscriptionDetailMapper.convertToProgramAreaSubscriptionDetailResponseEntity(programAreaSubscription);
			responseData.add(programAreaSubscriptionDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "programAreaSubscriptionDetail", value="/programAreaSubscription/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long programAreaSubscriptionId) {
		ProgramAreaSubscription programAreaSubscription = programAreaSubscriptionService.get(programAreaSubscriptionId);
		ProgramAreaSubscriptionDetailResponseEntity responseData = ProgramAreaSubscriptionDetailMapper.convertToProgramAreaSubscriptionDetailResponseEntity(programAreaSubscription);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "programAreaSubscriptionUpdate", value="/programAreaSubscription/{id}", method = RequestMethod.PUT)
	public ProgramAreaSubscriptionDetailResponseEntity put(@PathVariable("id") long programAreaSubscriptionId, @RequestBody ProgramAreaSubscriptionDetailRequestEntity requestData) {
		ProgramAreaSubscription programAreaSubscription = programAreaSubscriptionService.get(programAreaSubscriptionId);
		ProgramAreaSubscriptionDetailMapper.patchProgramAreaSubscription(programAreaSubscription, requestData);
		if (requestData.getProgramId()!=null) programAreaSubscription.setProgramId(programService.get(requestData.getProgramId())); 
		programAreaSubscriptionService.update(programAreaSubscription);
		ProgramAreaSubscriptionDetailResponseEntity responseData = ProgramAreaSubscriptionDetailMapper.convertToProgramAreaSubscriptionDetailResponseEntity(programAreaSubscription);
		return responseData;
	}

	//TODO: Same country code cannot be subscribed twice by a any program.
	@RequestMapping(name="programAreaSubscriptionCreate", value="/programAreaSubscription", method=RequestMethod.POST)
	public BaseListResponse post(@RequestBody ProgramAreaSubscriptionDetailRequestEntity requestData, Pageable pageable) {
		Long programId = requestData.getProgramId();
		ProgramAreaSubscription programAreaSubscription = new ProgramAreaSubscription();
		ProgramAreaSubscriptionDetailMapper.patchProgramAreaSubscription(programAreaSubscription, requestData);
		if (requestData.getProgramId()!=null) programAreaSubscription.setProgramId(programService.get(requestData.getProgramId()));
		programAreaSubscriptionService.update(programAreaSubscription);

		//Copied from list of subscriptions by program id - optimize later
		Page<ProgramAreaSubscription> programAreaSubscriptionPage = programAreaSubscriptionService.findByProgramId(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAreaSubscriptionDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAreaSubscriptionDetailMapper.setPagingParameters(programAreaSubscriptionPage);
		response.setPaging(paging);
		
		List<ProgramAreaSubscription> programAreaSubscriptionList = programAreaSubscriptionPage.getContent();
		for(ProgramAreaSubscription pas : programAreaSubscriptionList)
		{
			ProgramAreaSubscriptionDetailResponseEntity programAreaSubscriptionDetailResponseEntity = ProgramAreaSubscriptionDetailMapper.convertToProgramAreaSubscriptionDetailResponseEntity(pas);
			responseData.add(programAreaSubscriptionDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name="programAreaSubscriptionDelete", value="/programAreaSubscription/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long programAreaSubscriptionId)
	{
		programAreaSubscriptionService.delete(programAreaSubscriptionId);
	}
	
	@RequestMapping(name="programAreaSubscriptionDeleteAndReturnList", value="/programAreaSubscriptionWithProgram/{programId}/{id}", method=RequestMethod.DELETE)
	public BaseListResponse delete(@PathVariable("programId") long programId, @PathVariable("id") long programAreaSubscriptionId, Pageable pageable)
	{
		programAreaSubscriptionService.delete(programAreaSubscriptionId);
		
		//Copied from list of subscriptions by program id - optimize later
		Page<ProgramAreaSubscription> programAreaSubscriptionPage = programAreaSubscriptionService.findByProgramId(programId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramAreaSubscriptionDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramAreaSubscriptionDetailMapper.setPagingParameters(programAreaSubscriptionPage);
		response.setPaging(paging);
		
		List<ProgramAreaSubscription> programAreaSubscriptionList = programAreaSubscriptionPage.getContent();
		for(ProgramAreaSubscription programAreaSubscription : programAreaSubscriptionList)
		{
			ProgramAreaSubscriptionDetailResponseEntity programAreaSubscriptionDetailResponseEntity = ProgramAreaSubscriptionDetailMapper.convertToProgramAreaSubscriptionDetailResponseEntity(programAreaSubscription);
			responseData.add(programAreaSubscriptionDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
}
