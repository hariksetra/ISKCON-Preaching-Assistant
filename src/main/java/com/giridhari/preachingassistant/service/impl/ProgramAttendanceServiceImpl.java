package com.giridhari.preachingassistant.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.ProgramAttendance;
import com.giridhari.preachingassistant.db.model.QProgramAttendance;
import com.giridhari.preachingassistant.db.repo.ProgramAttendanceRepo;
import com.giridhari.preachingassistant.service.ProgramAttendanceService;
import com.giridhari.preachingassistant.util.CollectionUtils;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class ProgramAttendanceServiceImpl implements ProgramAttendanceService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	ProgramAttendanceRepo programAttendanceRepo;
	
	@Override
	public Page<ProgramAttendance> list(Pageable pageable) {
		return programAttendanceRepo.findAll(pageable);
	}
	
	@Override
	public Page<ProgramAttendance> programAttendanceQuery(HashMap<String, String> requestData, Pageable pageable) throws ParseException
	{
		QProgramAttendance programAttendance = QProgramAttendance.programAttendance;
		JPAQuery<ProgramAttendance> query = new JPAQuery<>(em);
		query = query.from(programAttendance);
		if(requestData.containsKey("startingDate") && !requestData.get("startingDate").equalsIgnoreCase("null"))
		{
			SimpleDateFormat dateFormatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			String startingDateStr = requestData.get("startingDate");
			Date startingDate = dateFormatter.parse(startingDateStr);
			if(requestData.containsKey("endingDate") && !requestData.get("endingDate").equalsIgnoreCase("null"))
			{
				String endingDateStr = requestData.get("startingDate");
				Date endingDate = dateFormatter.parse(endingDateStr);
				query = query.where(programAttendance.attendanceDate.between(startingDate, endingDate));
			}
			else
			{
				query = query.where(programAttendance.attendanceDate.eq(startingDate));
			}
		}
		
		if(requestData.containsKey("programId") && !requestData.get("programId").equalsIgnoreCase("null"))
		{
			query = query.where(programAttendance.programId.id.eq(Long.parseLong(requestData.get("programId"))));
		}
		if(requestData.containsKey("devoteeId") && !requestData.get("devoteeId").equalsIgnoreCase("null"))
		{
			query = query.where(programAttendance.devoteeId.id.eq(Long.parseLong(requestData.get("devoteeId"))));
		}
		return CollectionUtils.getPage(query, pageable);
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

}
