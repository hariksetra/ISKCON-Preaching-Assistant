package com.giridhari.preachingassistant.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.ProgramSession;
import com.giridhari.preachingassistant.rest.model.programattendance.ProgramAttendanceGeneralReport;

@Service
public interface ProgramAttendanceService {

	public Page<ProgramAttendance> list(Pageable pageable);
	
	public Page<ProgramAttendance> attendanceBySession(ProgramSession session, Pageable pageable);
	
	public ProgramAttendance get(long attendanceId);
	
	public void update(ProgramAttendance programAttendance);
	
	public void delete(long attendanceId);
	
	public ProgramAttendanceGeneralReport getProgramAttendanceGeneralReport(Program program);
}
