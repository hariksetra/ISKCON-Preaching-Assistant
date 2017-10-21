package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.yatra.YatraDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.yatra.YatraDetailResponseEntity;

public class YatraMapper {

	public static YatraDetailResponseEntity convertYatraToDetailedResponseEntity(Yatra yatra) {
		YatraDetailResponseEntity response = new YatraDetailResponseEntity();
		
		response.setId(yatra.getId());
		response.setYatraAddress(yatra.getYatraAddress());
		if (yatra.getYatraAdmin() != null) {
			response.setYatraAdmin(yatra.getYatraAdmin().getId());
		}
		response.setYatraName(yatra.getYatraName());
		response.setYatraType(yatra.getYatraType());
		
		return response;
	}
	
	public static void patchYatra(Yatra yatra, YatraDetailRequestEntity request) {
		if (request.getId() != null) {
			yatra.setId(request.getId());
		}
		
		if (request.getYatraAddress() != null) {
			yatra.setYatraAddress(request.getYatraAddress());
		}
		
		if (request.getYatraAdmin() != null) {
			Devotee yatraAdmin = new Devotee();
			yatraAdmin.setId(request.getYatraAdmin());
			yatra.setYatraAdmin(yatraAdmin);
		}
		
		if (request.getYatraName() != null) {
			yatra.setYatraName(request.getYatraName());
		}
		
		if (request.getYatraType() != null) {
			yatra.setYatraType(request.getYatraType());
		}
	}
	
	public static Paging setPagingParameters(Page<Yatra> yatraPage)
	 {
		 Paging paging = new Paging();
		 paging.setFirst(yatraPage.isFirst());
		 paging.setLast(yatraPage.isLast());
		 paging.setNumberOfElements(yatraPage.getNumberOfElements());
		 paging.setPageNumber(yatraPage.getNumber());
		 paging.setPageSize(yatraPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(yatraPage.getSort() != null)
			 paging.setSortedOrder(yatraPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(yatraPage.getTotalElements());
		 paging.setTotalPages(yatraPage.getTotalPages());
		 return paging;
	 }
}
