package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.mapper.DevoteeMapper;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListReponse;
import com.giridhari.preachingassistant.service.DevoteeService;

@RestController
public class DevoteeController {

	@Resource
	private DevoteeService devoteeService;

	@RequestMapping(name = "/devotees", value="/devotees", method = RequestMethod.GET)
	public BaseListReponse list() {
		BaseListReponse response = new BaseListReponse();
		List<Devotee> devoteeList = devoteeService.list();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		for(Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(devotee);
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "devoteeDetail", value="/devotees/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long devoteeId) {
		Devotee devotee = devoteeService.get(devoteeId);
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "devoteeUpdate", value="/devotees/{id}", method = RequestMethod.PUT)
	public DevoteeDetailResponseEntity put(@PathVariable("id") long devoteeId, @RequestBody DevoteeDetailRequestEntity requestData) {
		Devotee devotee = devoteeService.get(devoteeId);
		DevoteeMapper.patchDevotee(devotee, requestData);
		devoteeService.update(devotee);
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return responseData;
	}

	@RequestMapping(name="devoteeCreate", value="/devotees", method=RequestMethod.POST)
	public DevoteeDetailResponseEntity post(@RequestBody DevoteeDetailRequestEntity requestData) {
		Devotee devotee = new Devotee();
		DevoteeMapper.patchDevotee(devotee, requestData);
		devoteeService.update(devotee);
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return responseData;
	}
	
	@RequestMapping(name = "myCapturedList", value="/my_captured_list/{id}", method = RequestMethod.GET)
	public BaseListReponse list(@PathVariable("id") long devoteeId) {
		BaseListReponse response = new BaseListReponse();
		List<Devotee> devoteeList = devoteeService.getMyCapturedList(devoteeId);
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		for(Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(devotee);
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}
}
