package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.DevoteeHistory;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.useraccount.UserLoginResponseEntity;

public class UserAccountMapper {
	public static UserLoginResponseEntity convertToLoginUserResponseEntity(UserAccount userAccount, Devotee devotee) {
		UserLoginResponseEntity responseData = new UserLoginResponseEntity();
		
		responseData.setDevoteeId(devotee.getId());
		responseData.setDevoteeName(devotee.getLegalName());
		responseData.setRole(userAccount.getType().toString());
		responseData.setUsername(userAccount.getUsername());
		return responseData;
	}
	
	public static Paging setPagingParameters(Page<UserAccount> userAccountPage)
	 {
		 Paging paging = new Paging();
		 paging.setFirst(userAccountPage.isFirst());
		 paging.setLast(userAccountPage.isLast());
		 paging.setNumberOfElements(userAccountPage.getNumberOfElements());
		 paging.setPageNumber(userAccountPage.getNumber());
		 paging.setPageSize(userAccountPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(userAccountPage.getSort() != null)
			 paging.setSortedOrder(userAccountPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(userAccountPage.getTotalElements());
		 paging.setTotalPages(userAccountPage.getTotalPages());
		 return paging;
	 }
}
