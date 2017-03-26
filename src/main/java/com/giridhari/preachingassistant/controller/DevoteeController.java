package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseListReponse;
import com.giridhari.preachingassistant.service.DevoteeService;

@RestController
public class DevoteeController {
	
	@Resource
	private DevoteeService devoteeService;
	
	@RequestMapping(name = "/devotees", method = RequestMethod.GET)
	public BaseListReponse list() {
		BaseListReponse response = new BaseListReponse();
		List<Devotee> devoteeList = devoteeService.list();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		for(Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = new DevoteeOverviewEntity();
			devoteeOverviewEntity.setId(devotee.getId());
			devoteeOverviewEntity.setName(devotee.getLegalName());
			devoteeOverviewEntity.setPhone(devotee.getSmsPhone());
			devoteeOverviewEntity.setIntroDate(devotee.getIntroDate());
			devoteeOverviewEntity.setPreferredLanguage(devotee.getPreferredLanguage());
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}
	
}
