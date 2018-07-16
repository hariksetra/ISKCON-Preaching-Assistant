package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programsession.ProgramSessionDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programsession.ProgramSessionDetailResponseEntity;

public class ProgramSessionDetailMapper {
	public static ProgramSessionDetailResponseEntity convertToProgramSessionDetailResponseEntity(ProgramSession programSession) {
		ProgramSessionDetailResponseEntity responseData = new ProgramSessionDetailResponseEntity();
		if (programSession == null) return null;
		responseData.setId(programSession.getId());
		if (programSession.getProgram() != null)
			responseData.setProgramId(programSession.getProgram().getId());
		responseData.setTopic(programSession.getTopic());
		responseData.setSessionDate(programSession.getSessionDate());
		return responseData;
	}

	public static void patchProgramSession(ProgramSession programSession, ProgramSessionDetailRequestEntity requestData) {
		if (requestData.getId()!= null)
			programSession.setId(requestData.getId());
		if (requestData.getProgramId()!=null) {
			Program program = new Program();
			program.setId(requestData.getProgramId());
			programSession.setProgram(program);
		}
		if (requestData.getTopic() != null)
			programSession.setTopic(requestData.getTopic());
		if (requestData.getSessionDate()!=null)
			programSession.setSessionDate(requestData.getSessionDate());
	}

	public static Paging setPagingParameters(Page<ProgramSession> programSessionPage) {
		 Paging paging = new Paging();
		 paging.setFirst(programSessionPage.isFirst());
		 paging.setLast(programSessionPage.isLast());
		 paging.setNumberOfElements(programSessionPage.getNumberOfElements());
		 paging.setPageNumber(programSessionPage.getNumber());
		 paging.setPageSize(programSessionPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(programSessionPage.getSort()!= null)
			 paging.setSortedOrder(programSessionPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(programSessionPage.getTotalElements());
		 paging.setTotalPages(programSessionPage.getTotalPages());
		 return paging;
	 }
}
