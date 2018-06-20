package com.giridhari.preachingassistant.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.repo.ProgramAttendanceRepo;
import com.giridhari.preachingassistant.service.ProgramAttendanceService;

@Service
public class ProgramAttendanceServiceImpl implements ProgramAttendanceService {

	@Resource
	ProgramAttendanceRepo programAttendanceRepo;
	
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
	public Page<ProgramAttendance> attendanceByProgramAndDate(Program program, Date attendanceDate, Pageable pageable) {
		return programAttendanceRepo.findByProgramIdAndAttendanceDate(program, attendanceDate, pageable);
	}

}
