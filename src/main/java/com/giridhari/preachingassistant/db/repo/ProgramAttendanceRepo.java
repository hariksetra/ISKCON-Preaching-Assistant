package com.giridhari.preachingassistant.db.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.giridhari.preachingassistant.db.model.ProgramAttendance;

@Repository
public interface ProgramAttendanceRepo extends PagingAndSortingRepository<ProgramAttendance,Long>{

}
