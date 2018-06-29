package org.feh.service;

import java.util.List;

import org.feh.model.ScheduledExecutorTask;

public interface TimerTaskModelService {
	
	public List<ScheduledExecutorTask> findAllClazzNotNull();
	
}
