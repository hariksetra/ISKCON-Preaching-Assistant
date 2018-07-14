package com.giridhari.preachingassistant.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.exception.AssignerNotFoundException;
import com.giridhari.preachingassistant.service.FollowUpAssignmentStrategy;
import com.giridhari.preachingassistant.service.FollowUpAutoAssigner;

import org.springframework.stereotype.Service;

@Service
public class FollowUpAutoAssignerImpl implements FollowUpAutoAssigner  {

	private Map<String, FollowUpAssignmentStrategy> strategyMap;
	
	public FollowUpAutoAssignerImpl() {
		this.strategyMap = new HashMap<>();
	}

	@Override
    public void addStrategy(String name, FollowUpAssignmentStrategy followUpAutoAssigner) {
		strategyMap.put(name, followUpAutoAssigner);
	}

	@Override
	public Set<String> getStrategies() {
		return strategyMap.keySet();
	}

	@Override
	public FollowUpAssignmentStrategy getStrategy(String name) throws AssignerNotFoundException {
		if (strategyMap == null || strategyMap.get(name) == null) {
			throw new AssignerNotFoundException(name);
		}
		return strategyMap.get(name);
	}

	@Override
	public void assign(Program program, String assignmetStrategy) throws AssignerNotFoundException {
		FollowUpAssignmentStrategy strategy = getStrategy(assignmetStrategy);

		strategy.assign(program);
	}

}