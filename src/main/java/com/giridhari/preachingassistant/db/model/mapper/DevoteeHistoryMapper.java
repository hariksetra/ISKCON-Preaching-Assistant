package com.giridhari.preachingassistant.db.model.mapper;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.Response;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailResponseEntity;


public class DevoteeHistoryMapper {
	
	public static DevoteeHistoryDetailResponseEntity convertToDevoteeHistoryDetailResponseEntity(DevoteeHistory devoteeHistory) 
	{
		DevoteeHistoryDetailResponseEntity responseData = new DevoteeHistoryDetailResponseEntity();
		
		responseData.setComment(devoteeHistory.getComment());
		responseData.setRating(devoteeHistory.getRating());
		responseData.setId(devoteeHistory.getId());
		if(devoteeHistory.getResponse() != null)
			responseData.setResponse(devoteeHistory.getResponse().toString());
		responseData.setTimeStamp(devoteeHistory.getTimeStamp());
		if(devoteeHistory.getDevotee() != null)
			responseData.setDevoteeId(devoteeHistory.getDevotee().getId());
		if(devoteeHistory.getFollowUpVolunteer() != null)
			responseData.setFollowUpVolunteerId(devoteeHistory.getFollowUpVolunteer().getId());
		
		return responseData;
	}

	public static void patchDevoteeHistory(DevoteeHistory devoteeHistory, DevoteeHistoryDetailRequestEntity requestData)
	{
		if(requestData.getComment()!=null)
			devoteeHistory.setComment(requestData.getComment());
		if(requestData.getRating()!=null)
			devoteeHistory.setRating(requestData.getRating());
		if(requestData.getResponse()!=null)
		{
			Response response = Response.valueOf(requestData.getResponse());
			devoteeHistory.setResponse(response);
		}
		if(requestData.getTimeStamp()!=null)
			devoteeHistory.setTimeStamp(devoteeHistory.getTimeStamp());
		if(requestData.getDevoteeId()!=null)
		{
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getDevoteeId());
			devoteeHistory.setDevotee(devotee);
		}
		if(requestData.getFollowUpVolunteerId()!=null)
		{
			FollowUpVolunteer followUpVolunteer = new FollowUpVolunteer();
			followUpVolunteer.setId(requestData.getFollowUpVolunteerId());
			devoteeHistory.setFollowUpVolunteer(followUpVolunteer);
		}
		
	}
	
}
