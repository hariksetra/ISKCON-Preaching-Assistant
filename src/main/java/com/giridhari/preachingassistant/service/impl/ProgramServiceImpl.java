package com.giridhari.preachingassistant.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.QProgram;
import com.giridhari.preachingassistant.db.repo.ProgramRepo;
import com.giridhari.preachingassistant.model.ProgramType;
import com.giridhari.preachingassistant.model.TargetAudience;
import com.giridhari.preachingassistant.service.ProgramService;
import com.giridhari.preachingassistant.util.CollectionUtils;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class ProgramServiceImpl implements ProgramService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	ProgramRepo programRepo;
	
	@Override
	public Page<Program> list(Pageable pageable) {
		return programRepo.findAll(pageable);
	}
	
	@Override
	public Page<Program> findByMentorId(long mentorId, Pageable pageable) {
		return programRepo.findByMentor_id(mentorId, pageable);
	}
	
	@Override
	public Page<Program> findByYatraId(long yatraId, Pageable pageable) {
		return programRepo.findByParentYatra_id(yatraId, pageable);
	}
	
	@Override
	public Page<Program> findByProgramType(ProgramType programType, Pageable pageable) {
		return programRepo.findByType(programType, pageable);
	}
	
	@Override
	public Page<Program> findByTargetAudience(TargetAudience targetAudience, Pageable pageable) {
		return programRepo.findByTargetAudience(targetAudience, pageable);
	}
	
	@Override
	public Page<Program> findByZipPostalCode(String zipPostalCode, Pageable pageable){
		return programRepo.findByAreasSubscribed_ZipPostalCode(zipPostalCode, pageable);
	}
	
	@Override
	public Page<Program> findByAttendee(long attendeeId, Pageable pageable){
		return programRepo.findByParticipants_Attendee_id(attendeeId, pageable);
	}
	
	@Override
	public Page<Program> searchProgram(HashMap<String, String> requestData, Pageable pageable)
	{
		QProgram program = QProgram.program;
		JPAQuery<Program> query = new JPAQuery<Program>(em);
		query = query.from(program);
		if(requestData.containsKey("searchString") && !requestData.get("searchString").equalsIgnoreCase("null"))
		{
			String searchString = requestData.get("searchString");
			query = query.
					where(program.name.contains(searchString).
					or(program.address.contains(searchString)).
					or(program.description.contains(searchString)));
		}
		if(requestData.containsKey("type") && !requestData.get("type").equalsIgnoreCase("null"))
		{
			query = query.where(program.type.eq(ProgramType.valueOf(requestData.get("type"))));
		}
		if(requestData.containsKey("targetAudience") && !requestData.get("targetAudience").equalsIgnoreCase("null"))
		{
			query = query.where(program.targetAudience.eq(TargetAudience.valueOf(requestData.get("targetAudience"))));
		}
		if(requestData.containsKey("yatraId") && !requestData.get("yatraId").equalsIgnoreCase("null"))
		{
			query = query.where(program.parentYatra.id.eq(Long.parseLong(requestData.get("yatraId"))));
		}
		return CollectionUtils.getPage(query, pageable);
	}
	
	@Override
	public Program get(long programId) {
		return programRepo.findOne(programId);
	}
	
	@Override
	public void update(Program program) {
		programRepo.save(program);
	}
	
	@Override
	public void delete(long programId) {
		programRepo.delete(programId);
	}
	
}
