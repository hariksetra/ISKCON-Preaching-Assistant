package com.giridhari.preachingassistant.service.impl;

import com.giridhari.preachingassistant.db.model.FollowUpAssignment;
import com.giridhari.preachingassistant.db.model.FollowUpVolunteer;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.ProgramAssignment;
import com.giridhari.preachingassistant.service.FollowUpAssignmentService;
import com.giridhari.preachingassistant.service.FollowUpAssignmentStrategy;
import com.giridhari.preachingassistant.service.FollowUpAutoAssigner;
import com.giridhari.preachingassistant.service.FollowUpVolunteerService;
import com.giridhari.preachingassistant.service.ProgramAssignmentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class DefaultFollowUpAssignmentStrategy implements FollowUpAssignmentStrategy {

    @Resource
    private FollowUpAssignmentService followUpAssignmentService;

    @Resource
    private ProgramAssignmentService programAssignmentService;

    @Resource
    private FollowUpVolunteerService followUpVolunteerService;

    @Resource
    private FollowUpAutoAssigner followUpAutoAssigner;

    @Override
    public void assign(Program program) {
        //Auto Assign Logic
		ArrayList<FollowUpAssignment> followUpAssignments = new ArrayList<FollowUpAssignment>();
		ArrayList<ProgramAssignment> programAssignments = new ArrayList<ProgramAssignment>();
		ArrayList<ProgramAssignment> unassignedParticipants = new ArrayList<ProgramAssignment>();
		ArrayList<ProgramAssignment> removalList = new ArrayList<ProgramAssignment>();
		ArrayList<FollowUpVolunteer> followUpVolunteer = new ArrayList<FollowUpVolunteer>(); 
		HashMap<Long, Long> assignmentCount = new HashMap<Long, Long>(); 
		
		int totalParticipants;
		int totalVolunteers;
		int noOfAssignmentsToVolunteer;
		//Hard coding the value as of now, make it configurable later
		int maxAssignments = 2;
		
		followUpAssignments = (ArrayList<FollowUpAssignment>) followUpAssignmentService.listOfAssignmentsOfProgram(program);
		programAssignments = (ArrayList<ProgramAssignment>) programAssignmentService.findByProgram(program);
		followUpVolunteer = (ArrayList<FollowUpVolunteer>) followUpVolunteerService.findFollowupVolunteerOfProgram(program);
		
		totalParticipants = programAssignments.size();
		totalVolunteers = followUpVolunteer.size();
		noOfAssignmentsToVolunteer = (int) Math.ceil(totalParticipants/totalVolunteers);
		
		//Offer a cap/ceil of max how many participants to be assigned to a volunteer 
		if (noOfAssignmentsToVolunteer > maxAssignments) {
			noOfAssignmentsToVolunteer = maxAssignments;
		}
		
		//Gather the unassigned participants
		for (ProgramAssignment participant: programAssignments) {
			boolean alreadyAssigned = false;
			for (FollowUpAssignment assignedParticipant: followUpAssignments) {
				if (participant.getAttendee().getId() == assignedParticipant.getAttendee().getId()) {
					alreadyAssigned = true;
					break;
				}
			}
			if (alreadyAssigned == false) {
				unassignedParticipants.add(participant);
			}
		}
		
		//Count how many attendees are already assigned to each volunteer
		for (FollowUpAssignment assignedVolunteer: followUpAssignments) {
			Long count =  assignmentCount.get(assignedVolunteer.getVolunteer().getId());
			if (count != null) {
				assignmentCount.put(assignedVolunteer.getVolunteer().getId(), ++count);
			} else assignmentCount.put(assignedVolunteer.getVolunteer().getId(), new Long(1));
		}
		
		//Assign attendees to volunteers
		for (FollowUpVolunteer volunteer: followUpVolunteer) {
			Long count =  assignmentCount.get(volunteer.getDevotee().getId());
			if (count == null) count = new Long(0);
			removalList = new ArrayList<ProgramAssignment>();
			
			for (ProgramAssignment participantAssignment: unassignedParticipants) {
				if (count >= noOfAssignmentsToVolunteer) break;
				FollowUpAssignment followUpAssignment = new FollowUpAssignment();
				followUpAssignment.setAttendee(participantAssignment.getAttendee());
				followUpAssignment.setProgram(program);
				followUpAssignment.setVolunteer(volunteer.getDevotee());
				
				//Make DB call to add new assignment
				followUpAssignmentService.update(followUpAssignment);
				
				//Remove from Unassigned list
				removalList.add(participantAssignment);
				
				//Increase the count of assignees for this volunteer
				count++;
				assignmentCount.put(volunteer.getDevotee().getId(), count);
			}
			for (ProgramAssignment toBeRemoved: removalList) {
				unassignedParticipants.remove(toBeRemoved);
			}
		}
    }

    @PostConstruct
    public void addToStrategy() {
        followUpAutoAssigner.addStrategy("DEFAULT", this);
    }

}