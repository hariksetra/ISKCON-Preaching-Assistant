package com.giridhari.preachingassistant.service;

import com.giridhari.preachingassistant.db.model.Program;

public interface FollowUpAssignmentStrategy {

    public void assign(Program program);

}