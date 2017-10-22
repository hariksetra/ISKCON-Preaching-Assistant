package com.giridhari.preachingassistant.controller;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.giridhari.preachingassistant.db.model.Type;
import com.giridhari.preachingassistant.model.CountryCode;
import com.giridhari.preachingassistant.model.Gender;
import com.giridhari.preachingassistant.model.IncomeScale;
import com.giridhari.preachingassistant.model.MaritalStatus;
import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.Response;
import com.giridhari.preachingassistant.model.SikshaLevel;
import com.giridhari.preachingassistant.model.TargetAudience;
import com.giridhari.preachingassistant.model.YatraType;

@RestController
public class EnumModelsController {
	
	@RequestMapping(name = "enumModels", value = "/enumModels", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, HashMap<String, ArrayList<String>>> list(){
		
		HashMap<String, HashMap<String, ArrayList<String>>> enumMap = new HashMap<String, HashMap<String, ArrayList<String>>>();
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> enumConstName = new ArrayList<String>();
		ArrayList<String> enumConstDesc = new ArrayList<String>();
		
		for(CountryCode countryCode : CountryCode.values())
		{
			enumConstName.add(countryCode.name());
			enumConstDesc.add(countryCode.toString());
		}
		map.put("countryCodeName", enumConstName);
		map.put("countryCodeDesc", enumConstDesc);
		enumMap.put("countryCode", map);
		
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(Gender gender: Gender.values())
		{
			enumConstName.add(gender.name());
			enumConstDesc.add(gender.toString());
		}
		map.put("genderName", enumConstName);
		map.put("genderDesc", enumConstDesc);
		enumMap.put("gender", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(IncomeScale incomeScale : IncomeScale.values())
		{
			enumConstName.add(incomeScale.name());
			enumConstDesc.add(incomeScale.toString());
		}
		map.put("incomeScaleName", enumConstName);
		map.put("incomeScaleDesc", enumConstDesc);
		enumMap.put("incomeScale", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(MaritalStatus maritalStatus : MaritalStatus.values())
		{
			enumConstName.add(maritalStatus.name());
			enumConstDesc.add(maritalStatus.toString());
		}
		map.put("maritalStatusName", enumConstName);
		map.put("maritalStatusDesc", enumConstDesc);
		enumMap.put("maritalStatus", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(ProgramType programType : ProgramType.values())
		{
			enumConstName.add(programType.name());
			enumConstDesc.add(programType.toString());
		}
		map.put("programTypeName", enumConstName);
		map.put("programTypeDesc", enumConstDesc);
		enumMap.put("programType", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(Response response : Response.values())
		{
			enumConstName.add(response.name());
			enumConstDesc.add(response.toString());
		}
		map.put("responseName", enumConstName);
		map.put("responseDesc", enumConstDesc);
		enumMap.put("response", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(SikshaLevel sikshaLevel : SikshaLevel.values())
		{
			enumConstName.add(sikshaLevel.name());
			enumConstDesc.add(sikshaLevel.toString());
		}
		map.put("sikshaLevelName", enumConstName);
		map.put("sikshaLevelDesc", enumConstDesc);
		enumMap.put("sikshaLevel", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(TargetAudience targetAudience : TargetAudience.values())
		{
			enumConstName.add(targetAudience.name());
			enumConstDesc.add(targetAudience.toString());
		}
		map.put("targetAudienceName", enumConstName);
		map.put("targetAudienceDesc", enumConstDesc);
		enumMap.put("targetAudience", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(YatraType yatraType : YatraType.values())
		{
			enumConstName.add(yatraType.name());
			enumConstDesc.add(yatraType.toString());
		}
		map.put("yatraTypeName", enumConstName);
		map.put("yatraTypeDesc", enumConstDesc);
		enumMap.put("yatraType", map);
		
		map = new HashMap<String, ArrayList<String>>();
		enumConstName = new ArrayList<String>();
		enumConstDesc = new ArrayList<String>();
		for(Type type : Type.values())
		{
			enumConstName.add(type.name());
			enumConstDesc.add(type.toString());
		}
		map.put("typeName", enumConstName);
		map.put("typeDesc", enumConstDesc);
		enumMap.put("type", map);
		
		return enumMap;
	}
}
