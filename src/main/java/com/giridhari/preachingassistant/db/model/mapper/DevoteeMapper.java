package com.giridhari.preachingassistant.db.model.mapper;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeDetailResponseEntity;
import com.giridhari.preachingassistant.rest.model.devotee.DevoteeOverviewEntity;

public class DevoteeMapper {

	public static DevoteeDetailResponseEntity convertToDevoteeDetailResponseEntity(Devotee devotee) {
		DevoteeDetailResponseEntity responseData = new DevoteeDetailResponseEntity();
		responseData.setAddress(devotee.getAddress());
		responseData.setArea(devotee.getArea());
		responseData.setCountry(devotee.getCountry());
		responseData.setPostalCode(devotee.getPostalCode());
		responseData.setBooksRead(devotee.getBooksRead());
		if(devotee.getCapturedBy() != null)
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
		if (devotee.getInitiatedName() != null && devotee.getInitiatedName() != "") {
			responseData.setDisplayName(devotee.getInitiatedName());
		} else {
			responseData.setDisplayName(devotee.getLegalName());
		}
		responseData.setMaritalStatus(devotee.getMaritalStatus());
		responseData.setMonthlyContribution(devotee.getMonthlyContribution());
		responseData.setOccupation(devotee.getOccupation());
		responseData.setOrganization(devotee.getOrganization());
		responseData.setPreferredLanguage(devotee.getPreferredLanguage());
		responseData.setSikshaLevel(devotee.getSikshaLevel());
		responseData.setSmsPhone(devotee.getSmsPhone());
		if (devotee.getUserAccount() != null)
			responseData.setUserAccountId(devotee.getUserAccount().getId());

		return responseData;
	}

	public static DevoteeOverviewEntity convertToDevoteeOverviewEntity(Devotee devotee) {
		DevoteeOverviewEntity devoteeOverviewEntity = new DevoteeOverviewEntity();
		devoteeOverviewEntity.setId(devotee.getId());
		if (devotee.getInitiatedName() != null && devotee.getInitiatedName() != "") {
			devoteeOverviewEntity.setName(devotee.getInitiatedName());
		} else {
			devoteeOverviewEntity.setName(devotee.getLegalName());
		}
		devoteeOverviewEntity.setPhone(devotee.getSmsPhone());
		devoteeOverviewEntity.setIntroDate(devotee.getIntroDate());
		devoteeOverviewEntity.setPreferredLanguage(devotee.getPreferredLanguage());

		return devoteeOverviewEntity;
	}

	public static void patchDevotee(Devotee devotee, DevoteeDetailRequestEntity requestData) {
		if (requestData.getLegalName() != null) {
			devotee.setLegalName(requestData.getLegalName());
		}

		if (requestData.getInitiatedName() != null)
			devotee.setInitiatedName(requestData.getInitiatedName());

		if (requestData.getAddress() != null)
			devotee.setAddress(requestData.getAddress());

		if (requestData.getArea() != null)
			devotee.setArea(requestData.getArea());
		
		if(requestData.getCountry() != null)
			devotee.setCountry(requestData.getCountry());
		
		if(requestData.getPostalCode() != null)
			devotee.setPostalCode(requestData.getPostalCode());
		
		if (requestData.getCapturedBy() != null) {
			Devotee capturedBy = new Devotee();
			capturedBy.setId(requestData.getCapturedBy());
			devotee.setCapturedBy(capturedBy);
		}

		if (requestData.getCapturedFor() != null)
			devotee.setCapturedFor(requestData.getCapturedFor());

		if (requestData.getDescription() != null)
			devotee.setDescription(requestData.getDescription());

		if (requestData.getDesignation() != null)
			devotee.setDesignation(requestData.getDesignation());

		if (requestData.getDob() != null)
			devotee.setDob(requestData.getDob());

		if (requestData.getEducation() != null)
			devotee.setEducation(requestData.getEducation());

		if (requestData.getEmail() != null)
			devotee.setEmail(requestData.getEmail());

		if (requestData.getFamilyInfo() != null)
			devotee.setFamilyInfo(requestData.getFamilyInfo());

		if (requestData.getGender() != null)
			devotee.setGender(requestData.getGender());

		if (requestData.getIncomeScale() != null)
			devotee.setIncomeScale(requestData.getIncomeScale());

		if (requestData.getInitiatedName() != null)
			devotee.setInitiatedName(requestData.getInitiatedName());

		if (requestData.getIntroDate() != null)
			devotee.setIntroDate(requestData.getIntroDate());

		if (requestData.getLegalName() != null)
			devotee.setLegalName(requestData.getLegalName());

		if (requestData.getMaritalStatus() != null)
			devotee.setMaritalStatus(requestData.getMaritalStatus());

		if (requestData.getMonthlyContribution() != null)
			devotee.setMonthlyContribution(requestData.getMonthlyContribution());

		if (requestData.getOccupation() != null)
			devotee.setOccupation(requestData.getOccupation());

		if (requestData.getOrganization() != null)
			devotee.setOrganization(requestData.getOrganization());

		if (requestData.getPreferredLanguage() != null)
			devotee.setPreferredLanguage(requestData.getPreferredLanguage());

		if (requestData.getSikshaLevel() != null)
			devotee.setSikshaLevel(requestData.getSikshaLevel());

		if (requestData.getSmsPhone() != null)
			devotee.setSmsPhone(requestData.getSmsPhone());

	}


}
