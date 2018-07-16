package com.giridhari.preachingassistant.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.db.repo.DevoteeRepo;
import com.giridhari.preachingassistant.db.repo.FollowUpAssignmentRepo;
import com.giridhari.preachingassistant.db.repo.ProgramAssignmentRepo;
import com.giridhari.preachingassistant.db.repo.ProgramAttendanceRepo;
import com.giridhari.preachingassistant.db.repo.ProgramSessionRepo;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceGeneralReport;
import com.giridhari.preachingassistant.service.ProgramAttendanceService;

@Service
public class ProgramAttendanceServiceImpl implements ProgramAttendanceService {

	@Resource
	ProgramAttendanceRepo programAttendanceRepo;
	
	@Resource
	ProgramAssignmentRepo programAssignmentRepo;
	
	@Resource
	FollowUpAssignmentRepo followUpAssignmentRepo;
	
	@Resource
	DevoteeRepo devoteeRepo;
	
	@Resource
	ProgramSessionRepo programSessionRepo;
	
	@Override
	public Page<ProgramAttendance> list(Pageable pageable) {
		return programAttendanceRepo.findAll(pageable);
	}

	@Override
	public ProgramAttendance get(long attendanceId) {
		return programAttendanceRepo.findOne(attendanceId);
	}

	@Override
	public void update(ProgramAttendance programAttendance) {
		programAttendanceRepo.save(programAttendance);

	}

	@Override
	public void delete(long attendanceId) {
		programAttendanceRepo.delete(attendanceId);

	}

	@Override
	public Page<ProgramAttendance> attendanceBySession(ProgramSession session, Pageable pageable) {
		return programAttendanceRepo.findBySession(session, pageable);
	}
	
	/*
	Heading: Program Name

	Name
	Mobile
	Followup Volunteer
	Marital Status

	Total Attendance
	Date (Topic): Atten
	Date (Topic): Atten
	*/
	@Override
	public ProgramAttendanceGeneralReport getProgramAttendanceGeneralReport(Program program) {
		//Return if input is invalid
		if (program == null) return null;
		//Instantiate the report
		ProgramAttendanceGeneralReport attendanceReport = new ProgramAttendanceGeneralReport();
		//Fill in column details
		attendanceReport.setProgramName(program.getName());
		
		List<ProgramAssignment> attendeeList = programAssignmentRepo.findByProgram(program);
		List<HashMap<String, String>> rowsData = new ArrayList<HashMap<String, String>>();
		List<String> columnNames = new ArrayList<String>();
		
		//populate ColHeading
		List<ProgramSession> sessionList = programSessionRepo.findByProgram(program);
		columnNames.add("Name");
		columnNames.add("Phone");
		columnNames.add("Followup Incharge");
		columnNames.add("Marital Status");
		columnNames.add("Total Attendance");
		for(ProgramSession session: sessionList) {
			String colHeading = session.getSessionDate() + "";
			columnNames.add(colHeading);
		}
		
		for (ProgramAssignment assignment: attendeeList) {
			HashMap<String, String> rowData = new HashMap<String, String>();
			
			//Populate the general column Names and values
			if (assignment.getAttendee().getInitiatedName() == null || assignment.getAttendee().getInitiatedName() == "") {
				rowData.put("Name", assignment.getAttendee().getLegalName());
			}else rowData.put("Name", assignment.getAttendee().getInitiatedName());
			
			rowData.put("Phone", assignment.getAttendee().getSmsPhone());
			
			FollowUpAssignment followUpAssignment = followUpAssignmentRepo.findByAttendeeAndProgram(assignment.getAttendee(), program);
			if (followUpAssignment == null) {
				rowData.put("Followup Incharge", "");
			} else {
				if (followUpAssignment.getVolunteer().getInitiatedName() == null || followUpAssignment.getVolunteer().getInitiatedName()=="") {
					rowData.put("Followup Incharge", followUpAssignment.getVolunteer().getLegalName());
				}else rowData.put("Followup Incharge", followUpAssignment.getVolunteer().getInitiatedName());
			}

			if (assignment.getAttendee().getMaritalStatus() != null) {
				rowData.put("Marital Status", assignment.getAttendee().getMaritalStatus().toString());
			}else rowData.put("Marital Status", "");

			rowData.put("Total Attendance", programAttendanceRepo.countByDevoteeAndSession_program(assignment.getAttendee(), program) + "");
				
			//Populate session wise attendance
			List<ProgramAttendance> allSessionsAttendedByDevotee = programAttendanceRepo.findByDevoteeAndSession_program(assignment.getAttendee(), program);
			for (ProgramAttendance programAttendanceByDevotee: allSessionsAttendedByDevotee) {
				String colHeading = programAttendanceByDevotee.getSession().getSessionDate() + "";  
				rowData.put(colHeading, "X");
			}
			rowsData.add(rowData);
		}
		attendanceReport.setColumnNames(columnNames);
		rowsData.sort(ProgramAttendanceServiceImpl.sortAttendanceReport);
		attendanceReport.setReportRows(rowsData);
		
		return attendanceReport;
	}
	
	public static Comparator<HashMap<String, String>>
			sortAttendanceReport = new Comparator<HashMap<String, String>>() {
			@Override
			public int compare(HashMap<String, String> rowA, HashMap<String, String> rowB) {
				int valA, valB;
				valA = (rowA.get("Total Attendance")!=null ? Integer.parseInt(rowA.get("Total Attendance")) :0);
				valB = (rowB.get("Total Attendance")!=null ? Integer.parseInt(rowB.get("Total Attendance")) :0);			
				return valB-valA;
			}
		};
}
