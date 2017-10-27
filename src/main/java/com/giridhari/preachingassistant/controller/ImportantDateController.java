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

import com.giridhari.preachingassistant.db.model.ImportantDate;
import com.giridhari.preachingassistant.db.model.mapper.ImportantDateDetailMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.importantdate.ImportantDateDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.importantdate.ImportantDateDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.ImportantDateService;

@RestController
public class ImportantDateController {
	@Resource
	ImportantDateService importantDateService;
	
	@Resource
	DevoteeService devoteeService;

	@RequestMapping(name="importantDatePage", value = "/importantDatePage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<ImportantDate> importantDatePage = importantDateService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<ImportantDateDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = ImportantDateDetailMapper.setPagingParameters(importantDatePage);
		response.setPaging(paging);
		
		List<ImportantDate> importantDateList = importantDatePage.getContent();
		for(ImportantDate importantDate : importantDateList)
		{
			ImportantDateDetailResponseEntity importantDateDetailResponseEntity = ImportantDateDetailMapper.convertToImportantDateDetailResponseEntity(importantDate);
			responseData.add(importantDateDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "importantDateDetail", value="/importantDate/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long importantDateId) {
		ImportantDate importantDate = importantDateService.get(importantDateId);
		ImportantDateDetailResponseEntity responseData = ImportantDateDetailMapper.convertToImportantDateDetailResponseEntity(importantDate);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "importantDateUpdate", value="/importantDate/{id}", method = RequestMethod.PUT)
	public ImportantDateDetailResponseEntity put(@PathVariable("id") long importantDateId, @RequestBody ImportantDateDetailRequestEntity requestData) {
		ImportantDate importantDate = importantDateService.get(importantDateId);
		ImportantDateDetailMapper.patchImportantDate(importantDate, requestData);
		if(requestData.getDevoteeId()!=null) importantDate.setDevotee(devoteeService.get(requestData.getDevoteeId()));
		importantDateService.update(importantDate);
		ImportantDateDetailResponseEntity responseData = ImportantDateDetailMapper.convertToImportantDateDetailResponseEntity(importantDate);
		return responseData;
	}

	@RequestMapping(name="importantDateCreate", value="/importantDate", method=RequestMethod.POST)
	public ImportantDateDetailResponseEntity post(@RequestBody ImportantDateDetailRequestEntity requestData) {
		ImportantDate importantDate = new ImportantDate();
		ImportantDateDetailMapper.patchImportantDate(importantDate, requestData);
		if(requestData.getDevoteeId()!=null) importantDate.setDevotee(devoteeService.get(requestData.getDevoteeId()));
		importantDateService.update(importantDate);
		ImportantDateDetailResponseEntity responseData = ImportantDateDetailMapper.convertToImportantDateDetailResponseEntity(importantDate);
		return responseData;
	}

	@RequestMapping(name="importantDateDelete", value="/importantDate/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long importantDateId)
	{
		importantDateService.delete(importantDateId);
	}
}