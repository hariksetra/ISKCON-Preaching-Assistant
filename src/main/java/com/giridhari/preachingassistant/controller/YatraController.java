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

import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.db.model.mapper.YatraMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.rest.model.yatra.YatraDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.yatra.YatraDetailResponse;
import com.giridhari.preachingassistant.rest.model.yatra.YatraDetailResponseEntity;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.YatraService;

@RestController
public class YatraController {
	
	@Resource
	private YatraService yatraService;
	
	@Resource
	private DevoteeService devoteeService;
	
	@RequestMapping(name = "listOfYatras", value="/yatra", method = RequestMethod.GET)
	public BaseListResponse list() {
		BaseListResponse response = new BaseListResponse();
		
		List<YatraDetailResponseEntity> responseData = new ArrayList<>();
		List<Yatra> yatraList = yatraService.list();
		
		for(Yatra yatra: yatraList) {
			YatraDetailResponseEntity yatraResponse = YatraMapper.convertYatraToDetailedResponseEntity(yatra);
			responseData.add(yatraResponse);
		}
		response.setData(responseData);
		
		return response;
	}
	
	@RequestMapping(name = "listOfYatrasPage", value="/yatraPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable) {
		Page<Yatra> yatraPage = yatraService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<YatraDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = YatraMapper.setPagingParameters(yatraPage);
		response.setPaging(paging);
		
		List<Yatra> yatraList = yatraPage.getContent();
		for(Yatra yatra: yatraList) {
			YatraDetailResponseEntity yatraResponse = YatraMapper.convertYatraToDetailedResponseEntity(yatra);
			responseData.add(yatraResponse);
		}
		response.setData(responseData);
		
		return response;
	}
	
	@RequestMapping(name = "listOfYatrasPageByAdminId", value="/yatraPageByAdminId/{adminId}", method = RequestMethod.GET)
	public BaseListResponse listByAdminId(@PathVariable("adminId") long adminId, Pageable pageable) {
		Page<Yatra> yatraPage = yatraService.getYatraByAdminId(adminId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<YatraDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = YatraMapper.setPagingParameters(yatraPage);
		response.setPaging(paging);
		
		List<Yatra> yatraList = yatraPage.getContent();
		for(Yatra yatra: yatraList) {
			YatraDetailResponseEntity yatraResponse = YatraMapper.convertYatraToDetailedResponseEntity(yatra);
			responseData.add(yatraResponse);
		}
		response.setData(responseData);
		
		return response;
	}
	
	@RequestMapping(name = "yatraDetail", value="/yatra/{id}", method = RequestMethod.GET)
	public YatraDetailResponse getByYatraId(@PathVariable("id") long yatraId) {
		Yatra yatra = yatraService.getById(yatraId);
		YatraDetailResponseEntity responseData = YatraMapper.convertYatraToDetailedResponseEntity(yatra);
		return new YatraDetailResponse(responseData);
	}
	
	@RequestMapping(name = "updateYatra", value="/yatra/{id}", method = RequestMethod.PUT)
	public YatraDetailResponse updateYatra(@PathVariable("id") long yatraId, @RequestBody YatraDetailRequestEntity requestData) {
		Yatra yatra = yatraService.getById(yatraId);
		YatraMapper.patchYatra(yatra, requestData);
		if (requestData.getYatraAdmin() != null)
			yatra.setYatraAdmin(devoteeService.get(requestData.getYatraAdmin()));
		yatraService.update(yatra);
		YatraDetailResponseEntity responseData = YatraMapper.convertYatraToDetailedResponseEntity(yatra);
		return new YatraDetailResponse(responseData);
	}
	
	@RequestMapping(name = "createYatra", value="/yatra", method = RequestMethod.POST)
	public YatraDetailResponse post(@RequestBody YatraDetailRequestEntity requestData) {
		Yatra yatra = new Yatra();
		YatraMapper.patchYatra(yatra, requestData);
		yatraService.create(yatra);
		YatraDetailResponseEntity responseData = YatraMapper.convertYatraToDetailedResponseEntity(yatra);
		return new YatraDetailResponse(responseData);
	}
	
	@RequestMapping(name = "deleteYatra", value="/yatra/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long yatraId) {
		yatraService.delete(yatraId);
	}
}
