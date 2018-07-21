package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.db.model.mapper.CaptureContactMapper;
import com.giridhari.preachingassistant.db.model.mapper.DevoteeMapper;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.capturecontact.CaptureContactDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.capturecontact.CaptureContactDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.CaptureContactService;
import com.giridhari.preachingassistant.service.DevoteeHistoryService;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.ProgramAssignmentService;
import com.giridhari.preachingassistant.service.ProgramService;
import com.giridhari.preachingassistant.util.BadRequestException;

@RestController
public class CaptureContactController {
	@Resource
	CaptureContactService captureContactService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	ProgramAssignmentService programAssignmentService;
	
	@Resource
	ProgramService programService;
	
	@Resource
	DevoteeHistoryService devoteeHistoryService;
	
	@RequestMapping(name="captureContact", value="/capture", method=RequestMethod.POST)
	public BaseDataResponse post(@RequestBody CaptureContactDetailRequestEntity requestData) {
		Devotee devotee = new Devotee();
		
		if (requestData == null) throw new BadRequestException("No data to enter into prayer book!");
		
		DevoteeDetailRequestEntity requestDevoteeData = requestData.getCapturedDevotee();
		
		//Find if devotee already exist (SMS Phone Matching)
		//TODO: Later we may have to match email too
		Devotee existingDevotee = devoteeService.findBySmsPhone(requestDevoteeData.getSmsPhone());
		if (existingDevotee==null) {
			//Add the devotee if not there
			DevoteeMapper.patchDevotee(devotee, requestDevoteeData);
			devoteeService.update(devotee);
		} else {
			devotee = existingDevotee;
			/*
			 * This should update any new info about the devotee
			 * Since patch devotee does not update a null field in request data
			 * this should not harm any old data that is present
			 */
			DevoteeMapper.patchDevotee(devotee, requestDevoteeData);
			devoteeService.update(devotee);
		}
		
		//Update Capture Contact
		Devotee capturedByDevotee = devoteeService.get(requestData.getCapturedById());
		CaptureContact captureContact = captureContactService.findByCapturedByAndCapturedDevotee(capturedByDevotee, devotee);
		if (captureContact == null) {
			captureContact = new CaptureContact();
			captureContact.setCapturedBy(capturedByDevotee);
			captureContact.setCapturedDevotee(devotee);
			if (requestData.getTimestamp() == null) {
				captureContact.setTimestamp(new Date());
			} else {
				captureContact.setTimestamp(requestData.getTimestamp());
			}
			captureContact.setIntoducedrAt(requestData.getIntroducedAt());
			captureContactService.update(captureContact);
		} else {
			//May consider updating time capture time so that the devotee can be there in recent captured list
		}
		
		//Assign the contact to the interested program
		Program program = null;
		if (requestData.getProgramInterestedIn() != null ) program = programService.get(requestData.getProgramInterestedIn());
		if (program != null) {
			ProgramAssignment programAssignment;
			programAssignment = programAssignmentService.findByAttendeeAndProgram(devotee.getId(), program.getId());
			if (programAssignment == null) {
				programAssignment = new ProgramAssignment();
				programAssignment.setAttendee(devotee);
				programAssignment.setDateAdded(new Date());
				programAssignment.setProgram(program);
				programAssignmentService.update(programAssignment);
			}
		}
		
		//Make an entry in the history table
		String captureMessage = "Recorded into prayerbook ";
		DevoteeHistory devoteeHistory = new DevoteeHistory();
		//Preparing intro comments
		if (requestData.getIntroducedAt() != null) captureMessage = captureMessage + "(" + requestData.getIntroducedAt() + ") ";
		captureMessage = "[" + captureMessage + "] ";
		if (requestData.getIntroComments() != null) captureMessage = captureMessage + requestData.getIntroComments();
		//Preparing history entry
		devoteeHistory.setComment(captureMessage);
		devoteeHistory.setCommentedByDevotee(capturedByDevotee);
		devoteeHistory.setRatedDevotee(devotee);
		devoteeHistory.setRating(requestData.getRating());
		devoteeHistory.setTimeStamp(requestData.getTimestamp());
		//updating history table
		devoteeHistoryService.update(devoteeHistory);
		
		DevoteeDetailResponseEntity responseData = DevoteeMapper.convertToDevoteeDetailResponseEntity(devotee);
		return new BaseDataResponse(responseData);
	}

	
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
