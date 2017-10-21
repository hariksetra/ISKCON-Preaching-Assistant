package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.model.Response;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devoteehistory.DevoteeHistoryDetailResponseEntity;

public class DevoteeHistoryMapper {
	
	public static DevoteeHistoryDetailResponseEntity convertToDevoteeHistoryDetailResponseEntity(DevoteeHistory devoteeHistory,
			String commentedByDevoteeName, String ratedDevoteeName) 
	{
		DevoteeHistoryDetailResponseEntity responseData = new DevoteeHistoryDetailResponseEntity();
		
		responseData.setComment(devoteeHistory.getComment());
		responseData.setRating(devoteeHistory.getRating());
		responseData.setId(devoteeHistory.getId());
		if(devoteeHistory.getResponse() != null)
			responseData.setResponse(devoteeHistory.getResponse().toString());
		responseData.setTimeStamp(devoteeHistory.getTimeStamp());
		if(devoteeHistory.getRatedDevotee() != null) {
			responseData.setRatedDevoteeId(devoteeHistory.getRatedDevotee().getId());
			responseData.setRatedDevoteeName(ratedDevoteeName);
		}
		if(devoteeHistory.getCommentedByDevotee() != null) {
			responseData.setCommentedByDevoteeId(devoteeHistory.getCommentedByDevotee().getId());
			responseData.setCommentedByDevoteeName(commentedByDevoteeName);
		}
		
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
		if(requestData.getRatedDevoteeId()!=null)
		{
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getRatedDevoteeId());
			devoteeHistory.setRatedDevotee(devotee);
		}
		if(requestData.getCommentedByDevoteeId()!=null)
		{
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getCommentedByDevoteeId());
			devoteeHistory.setCommentedByDevotee(devotee);
		}
	}
	
	 public static Paging setPagingParameters(Page<DevoteeHistory> devoteeHistoryPage)
	 {
		 Paging paging = new Paging();
		 paging.setFirst(devoteeHistoryPage.isFirst());
		 paging.setLast(devoteeHistoryPage.isLast());
		 paging.setNumberOfElements(devoteeHistoryPage.getNumberOfElements());
		 paging.setPageNumber(devoteeHistoryPage.getNumber());
		 paging.setPageSize(devoteeHistoryPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 paging.setSortedOrder(devoteeHistoryPage.getSort().toString());
		 paging.setTotalElements(devoteeHistoryPage.getTotalElements());
		 paging.setTotalPages(devoteeHistoryPage.getTotalPages());
		 return paging;
	 }
}
