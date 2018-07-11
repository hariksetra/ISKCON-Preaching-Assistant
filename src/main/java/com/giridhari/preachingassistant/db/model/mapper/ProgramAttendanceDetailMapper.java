package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceDetailResponseEntity;

public class ProgramAttendanceDetailMapper {
	public static ProgramAttendanceDetailResponseEntity convertToProgramAttendanceDetailResponseEntity(ProgramAttendance programAttendance) {
		ProgramAttendanceDetailResponseEntity responseData = new ProgramAttendanceDetailResponseEntity();
		responseData.setId(programAttendance.getId());
		if (programAttendance.getSession()!=null)
			responseData.setSessionId(programAttendance.getSession().getId());
		if (programAttendance.getDevotee()!=null)
			responseData.setDevoteeId(programAttendance.getDevotee().getId());
		return responseData;
	}

	public static void patchProgramAttendance(ProgramAttendance programAttendance, ProgramAttendanceDetailRequestEntity requestData) {
		if (requestData.getId()!=null)
			programAttendance.setId(requestData.getId());
		if (requestData.getSessionId()!=null) {
			ProgramSession session = new ProgramSession();
			session.setId(requestData.getSessionId());
			programAttendance.setSession(session);
		}
		if (requestData.getDevoteeId()!=null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getDevoteeId());
			programAttendance.setDevotee(devotee);
		}
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
