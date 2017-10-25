package com.giridhari.preachingassistant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ProgramAttendance;

@Service
public interface ProgramAttendanceService {

	public Page<ProgramAttendance> list(Pageable pageable);
	
	public ProgramAttendance get(long attendanceId);
	
	public void update(ProgramAttendance programAttendance);
	
	public void delete(long attendanceId);
}
