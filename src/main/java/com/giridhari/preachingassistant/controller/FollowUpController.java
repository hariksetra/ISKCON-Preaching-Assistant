package com.giridhari.preachingassistant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.FollowUp;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.db.model.mapper.FollowUpDetailMapper;
import com.giridhari.preachingassistant.model.Response;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.followup.FollowUpDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.followup.FollowUpDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.response.BaseDataResponse;
import com.giridhari.preachingassistant.rest.model.response.BaseListResponse;
import com.giridhari.preachingassistant.service.DevoteeHistoryService;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.service.FollowUpService;
import com.giridhari.preachingassistant.service.ProgramService;
import com.giridhari.preachingassistant.service.ProgramSessionService;
import com.giridhari.preachingassistant.util.BadRequestException;

@RestController
public class FollowUpController {
	@Resource
	FollowUpService followUpService;
	
	@Resource
	DevoteeService devoteeService;
	
	@Resource
	DevoteeHistoryService devoteeHistoryService;
	
	@Resource
	ProgramService programService;
	
	@Resource
	ProgramSessionService programSessionService;

	@RequestMapping(name="followUpPage", value = "/followUpPage", method = RequestMethod.GET)
	public BaseListResponse list(Pageable pageable)
	{
		Page<FollowUp> followUpPage = followUpService.list(pageable);
		BaseListResponse response = new BaseListResponse();
		List<FollowUpDetailResponseEntity> responseData = new ArrayList<>();
		
		Paging paging = FollowUpDetailMapper.setPagingParameters(followUpPage);
		response.setPaging(paging);
		
		List<FollowUp> followUpList = followUpPage.getContent();
		for(FollowUp followUp : followUpList)
		{
			FollowUpDetailResponseEntity followUpDetailResponseEntity = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
			responseData.add(followUpDetailResponseEntity);
		}
		response.setData(responseData);
		return response;
	}

	@RequestMapping(name = "followUpDetail", value="/followUp/{id}", method = RequestMethod.GET)
	public BaseDataResponse get(@PathVariable("id") long followUpId) {
		FollowUp followUp = followUpService.get(followUpId);
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		ProgramSession programSession = programSessionService.get(followUp.getFollowupForSession().getId());
		responseData.setFollowupForSessionId(programSession.getId());
		responseData.setFollowupForSessionDate(programSession.getSessionDate());
		responseData.setFollowupForSessionTopic(programSession.getTopic());
		return new BaseDataResponse(responseData);
	}
	
	@RequestMapping(name = "followUpDetailSpecific", value="/specificFollowUpRecord/{programId}/{attendeeId}/{volunteerId}", method = RequestMethod.GET)
	public BaseDataResponse getFollowUpRecord(@PathVariable("programId") long programId, @PathVariable("attendeeId") long attendeeId, @PathVariable("volunteerId") long volunteerId) {
		Program program = programService.get(programId);
		//If there is no followup session is been assigned return error 
		if (program.getCurrentFollowupSession() == null) throw new BadRequestException("No such followup record");
		
		Devotee attendee = devoteeService.get(attendeeId);
		Devotee volunteer = devoteeService.get(volunteerId);
		FollowUp followUp = followUpService.getFollowUpRecord(program, attendee, volunteer, program.getCurrentFollowupSession());
		if (followUp == null) {
			System.out.println("Creating New Followup Record");
			followUp = new FollowUp();
			followUp.setAttendee(attendee);
			followUp.setProgram(program);
			followUp.setVolunteer(volunteer);
			followUp.setRating(0);
			followUp.setResponse(Response.CALL_AGAIN);
			followUp.setTimestamp(new Date());
			followUp.setFollowupForSession(program.getCurrentFollowupSession());
			followUpService.update(followUp);
		}
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		ProgramSession programSession = programSessionService.get(program.getCurrentFollowupSession().getId());
		responseData.setFollowupForSessionId(programSession.getId());
		responseData.setFollowupForSessionDate(programSession.getSessionDate());
		responseData.setFollowupForSessionTopic(programSession.getTopic());
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name = "followUpUpdate", value="/followUp/{id}", method = RequestMethod.PUT)
	public FollowUpDetailResponseEntity put(@PathVariable("id") long followUpId, @RequestBody FollowUpDetailRequestEntity requestData) {
		FollowUp followUp = followUpService.get(followUpId);
		FollowUpDetailMapper.patchFollowUp(followUp, requestData);
		if(requestData.getAttendeeId()!=null) followUp.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if(requestData.getVolunteerId()!=null) followUp.setVolunteer(devoteeService.get(requestData.getVolunteerId()));
		if(requestData.getProgramId()!=null) followUp.setProgram(programService.get(requestData.getProgramId()));
		followUpService.update(followUp);
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		return responseData;
	}
	
	@RequestMapping(name = "followUpOffilneUpdate", value="/followUpOffilneUpdate", 
			method = RequestMethod.PUT)
	public BaseDataResponse followUpOffilneUpdate(@RequestBody FollowUpDetailRequestEntity requestData) {
		
		if (requestData == null) throw new BadRequestException("No followup details received for update");
		if (requestData.getAttendeeId() == null || requestData.getProgramId() == null || 
				requestData.getVolunteerId() == null) {
			throw new BadRequestException("No such followup record");
		}
		
		Program program = programService.get(requestData.getProgramId());
		Devotee attendee = devoteeService.get(requestData.getAttendeeId());
		Devotee volunteer = devoteeService.get(requestData.getVolunteerId());
		
		//If there is no followUp session is been assigned return error 
		if (program == null || program.getCurrentFollowupSession() == null || 
				attendee == null || volunteer == null)
			throw new BadRequestException("No such followup record");
		
		
		FollowUp followUp = followUpService.getFollowUpRecord(program, attendee, volunteer, program.getCurrentFollowupSession());
		if (followUp == null) {
			System.out.println("Creating New Followup Record");
			followUp = new FollowUp();
			FollowUpDetailMapper.patchFollowUp(followUp, requestData);
			followUp.setFollowupForSession(program.getCurrentFollowupSession());
		} else {
			//If followUp record exist, we will update the recent followUp update
			//Due to offline support we may receive a older followUp update later in time
			//Which should not be updated
			if (followUp.getTimestamp().compareTo(requestData.getTimestamp()) < 0) {
				FollowUpDetailMapper.patchFollowUp(followUp, requestData);
			}
		}
		
		//Update the followUp Record
		if(requestData.getAttendeeId()!=null) followUp.setAttendee(attendee);
		if(requestData.getVolunteerId()!=null) followUp.setVolunteer(volunteer);
		if(requestData.getProgramId()!=null) followUp.setProgram(program);
		followUpService.update(followUp);
		
		//Update the history too
		DevoteeHistory devoteeHistory = new DevoteeHistory();
		devoteeHistory.setComment("[Followed up for " + program.getName() + ", Responded as:" + requestData.getResponse() + "] " 
				+ requestData.getComment());
		devoteeHistory.setCommentedByDevotee(volunteer);
		devoteeHistory.setRatedDevotee(attendee);
		devoteeHistory.setRating(requestData.getRating());
		devoteeHistory.setResponse(requestData.getResponse());
		devoteeHistory.setTimeStamp(requestData.getTimestamp());
		devoteeHistoryService.update(devoteeHistory);
		
		//Response entity
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		ProgramSession programSession = programSessionService.get(program.getCurrentFollowupSession().getId());
		responseData.setFollowupForSessionId(programSession.getId());
		responseData.setFollowupForSessionDate(programSession.getSessionDate());
		responseData.setFollowupForSessionTopic(programSession.getTopic());
		return new BaseDataResponse(responseData);
	}

	@RequestMapping(name="followUpCreate", value="/followUp", method=RequestMethod.POST)
	public FollowUpDetailResponseEntity post(@RequestBody FollowUpDetailRequestEntity requestData) {
		FollowUp followUp = new FollowUp();
		FollowUpDetailMapper.patchFollowUp(followUp, requestData);
		if(requestData.getAttendeeId()!=null) followUp.setAttendee(devoteeService.get(requestData.getAttendeeId()));
		if(requestData.getVolunteerId()!=null) followUp.setVolunteer(devoteeService.get(requestData.getVolunteerId()));
		if(requestData.getProgramId()!=null) followUp.setProgram(programService.get(requestData.getProgramId()));
		followUpService.update(followUp);
		FollowUpDetailResponseEntity responseData = FollowUpDetailMapper.convertToFollowUpDetailResponseEntity(followUp);
		return responseData;
	}

	@RequestMapping(name="followUpDelete", value="/followUp/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") long followUpId)
	{
		followUpService.delete(followUpId);
	}
	
	@RequestMapping(name="followUpDelete", value="/deleteFollowUpOfProgram/{programId}", method=RequestMethod.DELETE)
	public void clearFollowupOfProgram(@PathVariable("programId") long programId)
	{
		followUpService.clearFollowupOfProgram(programService.get(programId));
	}
}