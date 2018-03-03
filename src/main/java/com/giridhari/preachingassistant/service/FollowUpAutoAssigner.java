package com.giridhari.preachingassistant.service;

import java.util.Set;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.exception.AssignerNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface FollowUpAutoAssigner {

    public void addStrategy(String name, FollowUpAssignmentStrategy strategy);

    public Set<String> getStrategies();

    public FollowUpAssignmentStrategy getStrategy(String name) throws AssignerNotFoundException;

    public void assign(Program program, String assignmetStrategy) throws AssignerNotFoundException;

}