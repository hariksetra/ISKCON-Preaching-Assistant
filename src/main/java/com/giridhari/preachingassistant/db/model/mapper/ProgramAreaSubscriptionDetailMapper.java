package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAreaSubscription;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programareasubscription.ProgramAreaSubscriptionDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programareasubscription.ProgramAreaSubscriptionDetailResponseEntity;

public class ProgramAreaSubscriptionDetailMapper {
	public static ProgramAreaSubscriptionDetailResponseEntity convertToProgramAreaSubscriptionDetailResponseEntity(ProgramAreaSubscription programAreaSubscription) {
		ProgramAreaSubscriptionDetailResponseEntity responseData = new ProgramAreaSubscriptionDetailResponseEntity();
		responseData.setId(programAreaSubscription.getId());
		if (programAreaSubscription.getProgramId() != null)
			responseData.setProgramId(programAreaSubscription.getProgramId().getId());
		responseData.setCountryCode(programAreaSubscription.getCountryCode());
		responseData.setZipPostalCode(programAreaSubscription.getZipPostalCode());
		return responseData;
	}

	public static void patchProgramAreaSubscription(ProgramAreaSubscription programAreaSubscription, ProgramAreaSubscriptionDetailRequestEntity requestData) {
		if (requestData.getId() != null)
			programAreaSubscription.setId(requestData.getId());
		if (requestData.getProgramId() != null) {
			Program program = new Program();
			program.setId(requestData.getProgramId());
			programAreaSubscription.setProgramId(program);
		}
		if (requestData.getCountryCode()!=null)
			programAreaSubscription.setCountryCode(requestData.getCountryCode());
		if (requestData.getZipPostalCode()!=null)
			programAreaSubscription.setZipPostalCode(requestData.getZipPostalCode());
	}

	public static Paging setPagingParameters(Page<ProgramAreaSubscription> programAreaSubscriptionPage) {
		 Paging paging = new Paging();
		 paging.setFirst(programAreaSubscriptionPage.isFirst());
		 paging.setLast(programAreaSubscriptionPage.isLast());
		 paging.setNumberOfElements(programAreaSubscriptionPage.getNumberOfElements());
		 paging.setPageNumber(programAreaSubscriptionPage.getNumber());
		 paging.setPageSize(programAreaSubscriptionPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(programAreaSubscriptionPage.getSort()!= null)
			 paging.setSortedOrder(programAreaSubscriptionPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(programAreaSubscriptionPage.getTotalElements());
		 paging.setTotalPages(programAreaSubscriptionPage.getTotalPages());
		 return paging;
	 }
}
