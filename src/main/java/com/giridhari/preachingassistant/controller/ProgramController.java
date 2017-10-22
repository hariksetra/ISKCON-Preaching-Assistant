package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.mapper.ProgramMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.program.ProgramDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.ProgramService;

@RestController
public class ProgramController {
	
	@Resource
	ProgramService programService;
	
	@RequestMapping(name="programPage", value = "/programPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<Program> programPage = programService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<ProgramDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ProgramMapper.setPagingParameters(programPage);
		response.setPaging(paging);
		
		List<Program> programList = programPage.getContent();
		for(Program program : programList)
		{
			ProgramDetailResponseEntity programDetailResponseEntity = ProgramMapper.convertToProgramDetailResponseEntity(program);
			responseData.add(programDetailResponseEntity);
		}
		response.setData(responseData);
		
		return response;
	}
}
