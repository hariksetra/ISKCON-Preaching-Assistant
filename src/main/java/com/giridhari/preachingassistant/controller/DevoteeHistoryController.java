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

import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.mapper.DevoteeHistoryMapper;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailResponse;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.service.DevoteeHistoryService;

@RestController
public class DevoteeHistoryController {
	
	@Resource
	private DevoteeHistoryService devoteeHistoryService;
	
	@RequestMapping(name = "/devoteeHistories", value="/devoteeHistories", method = RequestMethod.GET)
	public BaseListResponse list() {
		BaseListResponse response = new BaseListResponse();
		List<DevoteeHistoryDetailResponseEntity> responseData = new ArrayList<>();
		List<DevoteeHistory> devoteeHistoryList = devoteeHistoryService.list();
		for(DevoteeHistory devoteeHistory: devoteeHistoryList) {
			DevoteeHistoryDetailResponseEntity devoteeHistoryDetailResponseEntity = 
					DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
							devoteeHistory,
							devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
							devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
						);
			responseData.add(devoteeHistoryDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name = "/devoteeHistoryPages", value="/devoteeHistoryPages", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable) {
		
		Page<DevoteeHistory> devoteeHistoryPage = devoteeHistoryService.listAllByPage(pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeHistoryDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = DevoteeHistoryMapper.setPagingParameters(devoteeHistoryPage);
		response.setPaging(paging);
		
		List<DevoteeHistory>  devoteeHistoryList = devoteeHistoryPage.getContent();
		for(DevoteeHistory devoteeHistory: devoteeHistoryList) {
			DevoteeHistoryDetailResponseEntity devoteeHistoryDetailResponseEntity = 
					DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
							devoteeHistory,
							devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
							devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
						);
			responseData.add(devoteeHistoryDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name = "devoteeHistoryDetail", value="/devoteeHistory/{id}", method = RequestMethod.GET)
	public DevoteeHistoryDetailResponse getById(@PathVariable("id") long devoteeHistoryId) {
		DevoteeHistory devoteeHistory = devoteeHistoryService.getById(devoteeHistoryId);
		DevoteeHistoryDetailResponseEntity responseData = 
				DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
						devoteeHistory,
						devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
						devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
					);
		return new DevoteeHistoryDetailResponse(responseData);
	}
	
	@RequestMapping(name = "devoteeHistoryDetailByRatedDevotee", value="/devoteeHistoryByRatedDevotee/{id}", method = RequestMethod.GET)
	public BaseListResponse getByRatedDevoteeId(@PathVariable("id") long ratedDevoteeId) {
		BaseListResponse response = new BaseListResponse();
		List<DevoteeHistoryDetailResponseEntity> responseData = new ArrayList<>();
		List<DevoteeHistory> devoteeHistoryList = devoteeHistoryService.getByRatedDevoteeId(ratedDevoteeId);
		for(DevoteeHistory devoteeHistory: devoteeHistoryList)
		{
			DevoteeHistoryDetailResponseEntity devoteeHistoryDetailResponseEntity = 
					DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
							devoteeHistory,
							devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
							devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
						);
			responseData.add(devoteeHistoryDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name = "devoteeHistoryDetailPageByRatedDevotee", value="/devoteeHistoryPageByRatedDevotee/{id}", method = RequestMethod.GET)
	public BaseListResponse getPageByRatedDevoteeId(@PathVariable("id") long ratedDevoteeId, Pageable pageable) {
		
		Page<DevoteeHistory> devoteeHistoryPage = devoteeHistoryService.getByRatedDevoteeId(ratedDevoteeId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeHistoryDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = DevoteeHistoryMapper.setPagingParameters(devoteeHistoryPage);
		response.setPaging(paging);
		
		List<DevoteeHistory> devoteeHistoryList = devoteeHistoryPage.getContent();
		for(DevoteeHistory devoteeHistory: devoteeHistoryList)
		{
			DevoteeHistoryDetailResponseEntity devoteeHistoryDetailResponseEntity = 
					DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
							devoteeHistory,
							devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
							devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
						);
			responseData.add(devoteeHistoryDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	
	
	@RequestMapping(name = "devoteeHistoryDetailByCommentedByDevotee", value="/devoteeHistoryByCommentedByDevotee/{id}", method = RequestMethod.GET)
	public BaseListResponse getByFollowUpVolunteerId(@PathVariable("id") long commentedByDevoteeId) {
		BaseListResponse response = new BaseListResponse();
		List<DevoteeHistoryDetailResponseEntity> responseData = new ArrayList<>();
		List<DevoteeHistory> devoteeHistoryList = devoteeHistoryService.getByCommentedByDevoteeId(commentedByDevoteeId);
		for(DevoteeHistory devoteeHistory: devoteeHistoryList)
		{
			DevoteeHistoryDetailResponseEntity devoteeHistoryDetailResponseEntity = 
					DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
							devoteeHistory,
							devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
							devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
						);
			responseData.add(devoteeHistoryDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name = "devoteeHistoryDetailPageByCommentedByDevotee", value="/devoteeHistoryPageByCommentedByDevotee/{id}", method = RequestMethod.GET)
	public BaseListResponse getPageByFollowUpVolunteerId(@PathVariable("id") long commentedByDevoteeId, Pageable pageable) {
		
		Page<DevoteeHistory> devoteeHistoryPage = devoteeHistoryService.getByCommentedByDevoteeId(commentedByDevoteeId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeHistoryDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = DevoteeHistoryMapper.setPagingParameters(devoteeHistoryPage);
		response.setPaging(paging);
		
		List<DevoteeHistory> devoteeHistoryList = devoteeHistoryPage.getContent();
		for(DevoteeHistory devoteeHistory: devoteeHistoryList)
		{
			DevoteeHistoryDetailResponseEntity devoteeHistoryDetailResponseEntity = 
					DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
							devoteeHistory,
							devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
							devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
						);
			responseData.add(devoteeHistoryDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "devoteeHistoryUpdate", value="/devoteeHistory/{id}", method = RequestMethod.PUT)
	public DevoteeHistoryDetailResponseEntity put(@PathVariable("id") long devoteeHistoryId, @RequestBody DevoteeHistoryDetailRequestEntity requestData) {
		DevoteeHistory devoteeHistory = devoteeHistoryService.getById(devoteeHistoryId);
		DevoteeHistoryMapper.patchDevoteeHistory(devoteeHistory, requestData);
		devoteeHistoryService.update(devoteeHistory);
		DevoteeHistoryDetailResponseEntity responseData = 
				DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
						devoteeHistory,
						devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
						devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
					);
		return responseData;
	}

	@RequestMapping(name="devoteeHistoryCreate", value="/devoteeHistory", method=RequestMethod.POST)
	public DevoteeHistoryDetailResponseEntity post(@RequestBody DevoteeHistoryDetailRequestEntity requestData) {
		DevoteeHistory devoteeHistory = new DevoteeHistory();
		DevoteeHistoryMapper.patchDevoteeHistory(devoteeHistory, requestData);
		devoteeHistoryService.create(devoteeHistory);
		DevoteeHistoryDetailResponseEntity responseData = 
				DevoteeHistoryMapper.convertToDevoteeHistoryDetailResponseEntity(
					devoteeHistory,
					devoteeHistoryService.getCommentedByDevoteeName(devoteeHistory),
					devoteeHistoryService.getRatedByDevoteeName(devoteeHistory)
				);
		return responseData;
	}
	
	@RequestMapping(name="devoteeHistoryDelete", value="/devoteeHistory/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long devoteeHistoryId)
	{
		devoteeHistoryService.delete(devoteeHistoryId);
	}
}
