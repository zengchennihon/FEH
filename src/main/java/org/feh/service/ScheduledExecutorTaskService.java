package org.feh.service;

import java.util.List;

import org.feh.model.ScheduledExecutorTask;

public interface ScheduledExecutorTaskService {
	
	public List<ScheduledExecutorTask> findAllClazzNotNull();
	
	public ScheduledExecutorTask findById(Integer id);
	
}
