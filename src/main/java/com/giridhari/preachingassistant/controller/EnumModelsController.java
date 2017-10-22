package com.giridhari.preachingassistant.controller;


import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.giridhari.preachingassistant.model.CountryCode;
import com.giridhari.preachingassistant.model.Gender;
import com.giridhari.preachingassistant.model.IncomeScale;
import com.giridhari.preachingassistant.model.MaritalStatus;
import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.Response;
import com.giridhari.preachingassistant.model.SikshaLevel;
import com.giridhari.preachingassistant.model.TargetAudience;
import com.giridhari.preachingassistant.model.YatraType;
import com.giridhari.preachingassistant.db.model.Type;

@RestController
public class EnumModelsController {
	
	@RequestMapping(name = "enumModels", value = "/enumModels", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, HashMap<String, String>> list(){
		
		HashMap<String, HashMap<String, String>> enumMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		for(CountryCode countryCode : CountryCode.values())
		{
			map.put(countryCode.name(), countryCode.toString());
		}
		enumMap.put("CountryCode", map);
		
		map = new HashMap<String, String>();
		for(Gender gender: Gender.values())
		{
			map.put(gender.name(), gender.toString());
		}
		enumMap.put("Gender", map);
		
		map = new HashMap<String, String>();
		for(IncomeScale incomeScale : IncomeScale.values())
		{
			map.put(incomeScale.name(), incomeScale.toString());
		}
		enumMap.put("IncomeScale", map);
		
		map = new HashMap<String, String>();
		for(MaritalStatus maritalStatus : MaritalStatus.values())
		{
			map.put(maritalStatus.name(), maritalStatus.toString());
		}
		enumMap.put("MaritalStatus", map);
		
		map = new HashMap<String, String>();
		for(ProgramType programType : ProgramType.values())
		{
			map.put(programType.name(), programType.toString());
		}
		enumMap.put("ProgramType", map);
		
		map = new HashMap<String, String>();
		for(Response response : Response.values())
		{
			map.put(response.name(), response.toString());
		}
		enumMap.put("Response", map);
		
		map = new HashMap<String, String>();
		for(SikshaLevel sikshaLevel : SikshaLevel.values())
		{
			map.put(sikshaLevel.name(), sikshaLevel.toString());
		}
		enumMap.put("SikshaLevel", map);
		
		map = new HashMap<String, String>();
		for(TargetAudience targetAudience : TargetAudience.values())
		{
			map.put(targetAudience.name(), targetAudience.toString());
		}
		enumMap.put("TargetAudience", map);
		
		map = new HashMap<String, String>();
		for(YatraType yatraType : YatraType.values())
		{
			map.put(yatraType.name(), yatraType.toString());
		}
		enumMap.put("YatraType", map);
		
		map = new HashMap<String, String>();
		for(Type type : Type.values())
		{
			map.put(type.name(), type.toString());
		}
		enumMap.put("Type", map);
		
		return enumMap;
	}
}
