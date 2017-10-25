package com.giridhari.preachingassistant.db.model.mapper;

import org.springframework.data.domain.Page;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.rest.model.Paging;
import com.giridhari.preachingassistant.rest.model.programassignment.ProgramAssignmentDetailRequestEntity;
import com.giridhari.preachingassistant.rest.model.programassignment.ProgramAssignmentDetailResponseEntity;

public class ProgramAssignmentDetailMapper {
	public static ProgramAssignmentDetailResponseEntity convertToProgramAssignmentDetailResponseEntity(ProgramAssignment programAssignment) {
		ProgramAssignmentDetailResponseEntity responseData = new ProgramAssignmentDetailResponseEntity();
		responseData.setId(programAssignment.getId());
		if (programAssignment.getProgram()!=null)
			responseData.setProgramId(programAssignment.getProgram().getId());
		responseData.setAttendeeId(programAssignment.getAttendee().getId());
		return responseData;
	}

	public static void patchProgram(ProgramAssignment programAssignment, ProgramAssignmentDetailRequestEntity requestData) {
		if(requestData.getId()!=null)
			programAssignment.setId(requestData.getId());
		if (requestData.getProgramId()!=null) {
			Program program = new Program();
			program.setId(requestData.getId());
			programAssignment.setProgram(program);
		}
		if (requestData.getAttendeeId()!=null) {
			Devotee devotee = new Devotee();
			devotee.setId(requestData.getAttendeeId());
			programAssignment.setAttendee(devotee);
		}
	}

	public static Paging setPagingParameters(Page<ProgramAssignment> programAssignmentPage) {
		 Paging paging = new Paging();
		 paging.setFirst(programAssignmentPage.isFirst());
		 paging.setLast(programAssignmentPage.isLast());
		 paging.setNumberOfElements(programAssignmentPage.getNumberOfElements());
		 paging.setPageNumber(programAssignmentPage.getNumber());
		 paging.setPageSize(programAssignmentPage.getSize());
		 //Sorted Order will tell the parameter over which it was sorted and direction of sort
		 if(programAssignmentPage.getSort()!= null)
			 paging.setSortedOrder(programAssignmentPage.getSort().toString());
		 else
			 paging.setSortedOrder(null);
		 paging.setTotalElements(programAssignmentPage.getTotalElements());
		 paging.setTotalPages(programAssignmentPage.getTotalPages());
		 return paging;
	 }
}
