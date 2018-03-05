package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.mapper.CaptureContactMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.capturecontact.CaptureContactDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.CaptureContactService;

@RestController
public class CaptureContactController {
	@Resource
	CaptureContactService captureContactService;
	
	@RequestMapping(name = "myCapturedListPage", value="/myCapturedListPage/{id}", method = RequestMethod.GET)
	public BaseListResponse list(@PathVariable("id") long devoteeId, Pageable pageable) {
		Page<CaptureContact> captureContactPage = captureContactService.findByCapturedBy(devoteeId, pageable);
		BaseListResponse response = new BaseListResponse();
		List<CaptureContactDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = CaptureContactMapper.setPagingParameters(captureContactPage);
		response.setPaging(paging);
		
		List<CaptureContact> captureContactList = captureContactPage.getContent();
		for(CaptureContact captureContact: captureContactList) {
			CaptureContactDetailResponseEntity captureContactResponseEntity = CaptureContactMapper.convertToCaptureContactDetailResponseEntity(captureContact);
			responseData.add(captureContactResponseEntity);
		}
		response.setData(responseData);
		return response;
	}
}
