package com.giridhari.preachingassistant.db.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.ProgramSession;

@Repository
public interface ProgramAttendanceRepo extends PagingAndSortingRepository<ProgramAttendance,Long>{
	public Page<ProgramAttendance> findBySession(ProgramSession session, Pageable pageable);
	public List<ProgramAttendance> findBySession_program(Program program);
	public long countByDevoteeAndSession_program(Devotee attendee, Program program);
	public List<ProgramAttendance> findByDevoteeAndSession_program(Devotee attendee, Program program);
	public ProgramAttendance findByDevotee_idAndSession_id(long attendeeId, long sessionId);
}
