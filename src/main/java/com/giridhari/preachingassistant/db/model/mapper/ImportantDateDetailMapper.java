package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.ImportantDate;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.importantdate.ImportantDateDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.importantdate.ImportantDateDetailResponseEntity;

public class ImportantDateDetailMapper {
	public static ImportantDateDetailResponseEntity convertToImportantDateDetailResponseEntity(ImportantDate importantDate) {
		ImportantDateDetailResponseEntity responseData = new ImportantDateDetailResponseEntity();
		responseData.setId(importantDate.getId());
		if (importantDate.getDevotee() != null)
			responseData.setDevoteeId(importantDate.getDevotee().getId());
		responseData.setSignificance(importantDate.getSignificance());
		responseData.setDate(importantDate.getDate());
		return responseData;
	}

	public static void patchImportantDate(ImportantDate importantDate, ImportantDateDetailRequestEntity requestData) {
		if (requestData.getId()!= null)
			importantDate.setId(requestData.getId());
		if (requestData.getDevoteeId()!=null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getDevoteeId());
			importantDate.setDevotee(devotee);
		}
		if (requestData.getSignificance() != null)
			importantDate.setSignificance(requestData.getSignificance());
		if (requestData.getDate()!=null)
			importantDate.setDate(requestData.getDate());
	}

	public static Paging setPagingParameters(Page<ImportantDate> importantDatePage) {
		 Paging paging = new Paging();
		 paging.setFirst(importantDatePage.isFirst());
		 paging.setLast(importantDatePage.isLast());
		 paging.setNumberOfElements(importantDatePage.getNumberOfElements());
		 paging.setPageNumber(importantDatePage.getNumber());
		 paging.setPageSize(importantDatePage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(importantDatePage.getSort()!= null)
			 paging.setSortedOrder(importantDatePage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(importantDatePage.getTotalElements());
		 paging.setTotalPages(importantDatePage.getTotalPages());
		 return paging;
	 }
}
