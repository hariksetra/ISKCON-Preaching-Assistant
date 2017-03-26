package com.giridhari.preachingassistant.db.model.mapper;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;

public class DevoteeMapper {
	
	public static DevoteeDetailResponseEntity convertToDevoteeDetailResponseEntity(Devotee devotee) {
		DevoteeDetailResponseEntity responseData = new DevoteeDetailResponseEntity();
		responseData.setAddress(devotee.getAddress());
		responseData.setArea(devotee.getArea());
		responseData.setBooksRead(devotee.getBooksRead());
		responseData.setCapturedBy(devotee.getCapturedBy().getId());
		responseData.setCapturedFor(devotee.getCapturedFor());
		responseData.setDescription(devotee.getDescription());
		responseData.setDesignation(devotee.getDesignation());
		responseData.setDob(devotee.getDob());
		responseData.setEducation(devotee.getEducation());
		responseData.setEmail(devotee.getEmail());
		responseData.setFamilyInfo(devotee.getFamilyInfo());
		responseData.setGender(devotee.getGender());
		responseData.setId(devotee.getId());
		responseData.setIncomeScale(devotee.getIncomeScale());
		responseData.setInitiatedName(devotee.getInitiatedName());
		responseData.setIntroDate(devotee.getIntroDate());
		responseData.setLegalName(devotee.getLegalName());
		responseData.setMaritalStatus(devotee.getMaritalStatus());
		responseData.setMonthlyContribution(devotee.getMonthlyContribution());
		responseData.setOccupation(devotee.getOccupation());
		responseData.setOrganization(devotee.getOrganization());
		responseData.setPreferredLanguage(devotee.getPreferredLanguage());
		responseData.setSikshaLevel(devotee.getSikshaLevel());
		responseData.setSmsPhone(devotee.getSmsPhone());
		responseData.setUserAccountId(devotee.getUserAccount().getId());
		
		return responseData;
	}
	
	public static DevoteeOverviewEntity convertToDevoteeOverviewEntity(Devotee devotee) {
		DevoteeOverviewEntity devoteeOverviewEntity = new DevoteeOverviewEntity();
		devoteeOverviewEntity.setId(devotee.getId());
		devoteeOverviewEntity.setName(devotee.getLegalName());
		devoteeOverviewEntity.setPhone(devotee.getSmsPhone());
		devoteeOverviewEntity.setIntroDate(devotee.getIntroDate());
		devoteeOverviewEntity.setPreferredLanguage(devotee.getPreferredLanguage());
		
		return devoteeOverviewEntity;
	}

}
