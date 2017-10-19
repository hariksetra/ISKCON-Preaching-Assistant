package com.giridhari.preachingassistant.controller;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.db.model.mapper.YatraMapper;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.rest.model.yatra.YatraDetailResponseEntity;
import com.giridhari.preachingassistant.service.YatraService;

@RestController
public class YatraController {
	
	@Resource
	private YatraService yatraService;
	
	@RequestMapping(name = "listOfYatras", value="/yatras", method = RequestMethod.GET)
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
}
