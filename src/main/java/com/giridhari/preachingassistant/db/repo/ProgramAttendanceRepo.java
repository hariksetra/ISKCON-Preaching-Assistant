package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.ProgramSession;

@Repository
public interface ProgramAttendanceRepo extends PagingAndSortingRepository<ProgramAttendance,Long>{
	public Page<ProgramAttendance> findBySession(ProgramSession session, Pageable pageable);
}
