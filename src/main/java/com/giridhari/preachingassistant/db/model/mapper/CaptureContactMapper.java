package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;
import com.giridhari.preachingassistant.db.model.CaptureContact;
import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.capturecontact.CaptureContactDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.capturecontact.CaptureContactDetailResponseEntity;

public class CaptureContactMapper {
	public static CaptureContactDetailResponseEntity convertToCaptureContactDetailResponseEntity(CaptureContact captureContact) {
		CaptureContactDetailResponseEntity responseData = new CaptureContactDetailResponseEntity();
		responseData.setId(captureContact.getId());
		if (captureContact.getCapturedBy() != null) {
			responseData.setCapturedById(captureContact.getCapturedBy().getId());
			if (captureContact.getCapturedBy().getInitiatedName() != null && captureContact.getCapturedBy().getInitiatedName() != "") {
				responseData.setCapturedByName(captureContact.getCapturedBy().getInitiatedName());
			} else {
				responseData.setCapturedByName(captureContact.getCapturedBy().getLegalName());
			}
		}
		if (captureContact.getCapturedDevotee()!=null) {
			responseData.setCapturedDevoteeId(captureContact.getCapturedDevotee().getId());
			responseData.setCapturedDevoteePhone(captureContact.getCapturedDevotee().getSmsPhone());
			if (captureContact.getCapturedDevotee().getInitiatedName() != null && captureContact.getCapturedDevotee().getInitiatedName() != "") {
				responseData.setCapturedDevoteeName(captureContact.getCapturedDevotee().getInitiatedName());
			} else {
				responseData.setCapturedDevoteeName(captureContact.getCapturedDevotee().getLegalName());
			}
		}
		
		if (captureContact.getIntoducedrAt()  !=null) {
			responseData.setIntroducedAt(captureContact.getIntoducedrAt());
		}
		responseData.setTimestamp(captureContact.getTimestamp());
		return responseData;
	}

	//Currently this function is not used. Patch task is done in controller/service itself
	public static void patchCaptureContact(CaptureContact captureContact, CaptureContactDetailRequestEntity requestData) {
		if (requestData.getId() != null)
			captureContact.setId(requestData.getId());
		
		if (requestData.getTimestamp() != null) {
			captureContact.setTimestamp(requestData.getTimestamp());
		}
		
		if (requestData.getIntroducedAt() != null) {
			captureContact.setIntoducedrAt(requestData.getIntroducedAt());
		}
	}

	public static Paging setPagingParameters(Page<CaptureContact> captureContactPage) {
		 Paging paging = new Paging();
		 paging.setFirst(captureContactPage.isFirst());
		 paging.setLast(captureContactPage.isLast());
		 paging.setNumberOfElements(captureContactPage.getNumberOfElements());
		 paging.setPageNumber(captureContactPage.getNumber());
		 paging.setPageSize(captureContactPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(captureContactPage.getSort()!= null)
			 paging.setSortedOrder(captureContactPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(captureContactPage.getTotalElements());
		 paging.setTotalPages(captureContactPage.getTotalPages());
		 return paging;
	 }

}