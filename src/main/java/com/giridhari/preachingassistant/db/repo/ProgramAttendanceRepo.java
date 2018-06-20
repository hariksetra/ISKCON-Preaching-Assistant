package com.giridhari.preachingassistant.db.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;

@Repository
public interface ProgramAttendanceRepo extends PagingAndSortingRepository<ProgramAttendance,Long>{
	public Page<ProgramAttendance> findByProgramIdAndAttendanceDate(Program program, Date attendanceDate, Pageable pageable);
}
