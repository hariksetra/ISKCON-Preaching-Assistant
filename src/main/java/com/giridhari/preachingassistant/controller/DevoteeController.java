package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Type;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.db.model.mapper.DevoteeMapper;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailResponse;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.service.CaptureContactService;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.UserService;
import com.giridhari.preachingassistant.util.NotFoundException;

@RestController
public class DevoteeController {

	@Resource
	private DevoteeService devoteeService;
	
	@Resource
	private UserService userService;

	@Resource
	private CaptureContactService captureContactService;

	@RequestMapping(name = "/devotees", value="/devotees", method = RequestMethod.GET)
	public BaseListResponse list() {
		BaseListResponse response = new BaseListResponse();
		List<Devotee> devoteeList = devoteeService.list();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		for(Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(devotee);
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "/devoteesPage", value="/devoteesPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable) {
		Page<Devotee> devoteePage = devoteeService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		
		Paging paging = DevoteeMapper.setPagingParameters(devoteePage);
		response.setPaging(paging);
		
		List<Devotee> devoteeList = devoteePage.getContent();
		for(Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(devotee);
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}
	
	@RequestMapping(name = "/devoteeGlobalSearchPage", value="/devoteeGlobalSearchPage/{typeText}", method = RequestMethod.GET)
	public BaseListResponse devoteeTypeAhead(@PathVariable("typeText") String typeText, Pageable pageable) {
		Page<Devotee> devoteePage = devoteeService.globalDevoteeSearchTypeAhead(typeText, pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();
		
		Paging paging = DevoteeMapper.setPagingParameters(devoteePage);
		response.setPaging(paging);
		
		List<Devotee> devoteeList = devoteePage.getContent();
		for(Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(devotee);
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "/devoteeSearch", value="/devoteeSearch", method = RequestMethod.GET)
	public BaseListResponse devoteeSearch(@RequestParam("q") String q, @RequestParam(value="programId", defaultValue="0") long programId, @RequestParam(value="yatraId", defaultValue="0") long yatraId, Pageable pageable, @AuthenticationPrincipal final User user) {
		UserAccount userAccount = userService.get(user.getUsername());

		long userDevoteeId = userAccount.getProfile().getId();
		Type role = userAccount.getType();
		Page<Devotee> devoteesPage = devoteeService.devoteeSearch(q, programId, yatraId, userDevoteeId, role, pageable);
		BaseListResponse response = new BaseListResponse();
		List<DevoteeOverviewEntity> responseData = new ArrayList<>();

		Paging paging = DevoteeMapper.setPagingParameters(devoteesPage);
		response.setPaging(paging);

		List<Devotee> devoteeList = devoteesPage.getContent();
		for (Devotee devotee: devoteeList) {
			DevoteeOverviewEntity devoteeOverviewEntity = DevoteeMapper.convertToDevoteeOverviewEntity(devotee);
			responseData.add(devoteeOverviewEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "devoteeDetail", value="/devotees/{id}", method = RequestMethod.GET)
	public DevoteeDetailResponse get(@PathVariable("id") long devoteeId) {
		Devotee devotee = devoteeService.get(devoteeId);
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return new DevoteeDetailResponse(responseData);
	}
	
	@RequestMapping(name = "devoteeDetailByEmail", value="/devoteeByEmail", method = RequestMethod.GET)
	public DevoteeDetailResponse getByEmail(@RequestParam("email") String email) {
		Devotee devotee = devoteeService.findByEmail(email);
		if (devotee !=null) {
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return new DevoteeDetailResponse(responseData);
		} else return null;
	}
	
	@RequestMapping(name = "devoteeDetailByPhone", value="/devoteeByPhone/{phone}", method = RequestMethod.GET)
	public DevoteeDetailResponse getByPhone(@PathVariable("phone") String phone) {
		Devotee devotee = devoteeService.findBySmsPhone(phone);
		
		if (devotee !=null) {
			DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
			return new DevoteeDetailResponse(responseData);
		} else throw new NotFoundException("contact not found");
	}

	@RequestMapping(name = "devoteeUpdate", value="/devotees/{id}", method = RequestMethod.PUT)
	public DevoteeDetailResponseEntity put(@PathVariable("id") long devoteeId, @RequestBody DevoteeDetailRequestEntity requestData) {
		Devotee devotee = devoteeService.get(devoteeId);
		DevoteeMapper.patchDevotee(devotee, requestData);
		
		devoteeService.update(devotee);
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return responseData;
	}

	//This is end point is obsolete DO NOT USE THIS: use /capture end point from capture contact controller
	@RequestMapping(name="devoteeCreate", value="/devotees", method=RequestMethod.POST)
	public DevoteeDetailResponse post(@RequestBody DevoteeDetailRequestEntity requestData) {
		Devotee devotee = new Devotee();
		
		String introducedAt;
		if (requestData.getDescription() != null) {
			introducedAt = requestData.getDescription();
		} else {
			introducedAt = "";
		}
		//Find if devotee already exist (SMS Phone Matching)
		//TODO: Later we may have to match email too
		Devotee existingDevotee = devoteeService.findBySmsPhone(requestData.getSmsPhone());
		if (existingDevotee==null) {
			//Add the devotee if not there
			DevoteeMapper.patchDevotee(devotee, requestData);
			devoteeService.update(devotee);
		} else {
			//TODO: Match any extra fields are there, update the fields
			devotee = existingDevotee;
		}
		
		//Update Capture Contact
		Devotee capturedByDevotee = devoteeService.get(requestData.getCapturedBy());
		CaptureContact captureContact = captureContactService.findByCapturedByAndCapturedDevotee(capturedByDevotee, devotee);
		if (captureContact == null) {
			captureContact = new CaptureContact();
			captureContact.setCapturedBy(capturedByDevotee);
			captureContact.setCapturedDevotee(devotee);
			captureContact.setTimestamp(new Date());
			captureContact.setIntoducedrAt(introducedAt);
			captureContactService.update(captureContact);
		}
		
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return new DevoteeDetailResponse(responseData);
	}
}
