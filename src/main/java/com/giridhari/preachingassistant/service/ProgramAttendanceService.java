package com.giridhari.preachingassistant.service;

import java.util.HashMap;

import java.text.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ProgramAttendance;

@Service
public interface ProgramAttendanceService{

	public Page<ProgramAttendance> list(Pageable pageable);
	
	public Page<ProgramAttendance> programAttendanceQuery(HashMap<String, String> requestData, Pageable pageable) throws ParseException;
	
	public ProgramAttendance get(long attendanceId);
	
	public void update(ProgramAttendance programAttendance);
	
	public void delete(long attendanceId);
}
