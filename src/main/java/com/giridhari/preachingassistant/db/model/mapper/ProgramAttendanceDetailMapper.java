package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailResponseEntity;

public class ProgramAttendanceDetailMapper {
	public static ProgramAttendanceDetailResponseEntity convertToProgramAttendanceDetailResponseEntity(ProgramAttendance programAttendance) {
		ProgramAttendanceDetailResponseEntity responseData = new ProgramAttendanceDetailResponseEntity();
		responseData.setId(programAttendance.getId());
		responseData.setAttendanceDate(programAttendance.getAttendanceDate());
		if (programAttendance.getProgramId()!=null)
			responseData.setProgramId(programAttendance.getProgramId().getId());
		if (programAttendance.getDevoteeId()!=null)
			responseData.setDevoteeId(programAttendance.getDevoteeId().getId());
		responseData.setTopic(programAttendance.getTopic());
		return responseData;
	}

	public static void patchProgram(ProgramAttendance programAttendance, ProgramAttendanceDetailRequestEntity requestData) {
		if (requestData.getId()!=null)
			programAttendance.setId(requestData.getId());
		if (requestData.getAttendanceDate()!=null)
			programAttendance.setAttendanceDate(requestData.getAttendanceDate());
		if (requestData.getProgramId()!=null) {
			Program program = new Program();
			program.setId(requestData.getProgramId());
			programAttendance.setProgramId(program);
		}
		if (requestData.getDevoteeId()!=null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getDevoteeId());
			programAttendance.setDevoteeId(devotee);
		}
		if (requestData.getTopic()!= null)
			programAttendance.setTopic(requestData.getTopic());
	}

	public static Paging setPagingParameters(Page<ProgramAttendance> programAttendancePage) {
		 Paging paging = new Paging();
		 paging.setFirst(programAttendancePage.isFirst());
		 paging.setLast(programAttendancePage.isLast());
		 paging.setNumberOfElements(programAttendancePage.getNumberOfElements());
		 paging.setPageNumber(programAttendancePage.getNumber());
		 paging.setPageSize(programAttendancePage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(programAttendancePage.getSort()!= null)
			 paging.setSortedOrder(programAttendancePage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(programAttendancePage.getTotalElements());
		 paging.setTotalPages(programAttendancePage.getTotalPages());
		 return paging;
	 }
}
