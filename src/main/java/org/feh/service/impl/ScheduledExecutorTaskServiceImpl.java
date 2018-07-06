package org.feh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.feh.dao.ScheduledExecutorTaskMapper;
import org.feh.model.ScheduledExecutorTask;
import org.feh.service.ScheduledExecutorTaskService;
import org.springframework.stereotype.Service;

@Service
public class ScheduledExecutorTaskServiceImpl implements ScheduledExecutorTaskService {
	
	@Resource
	private ScheduledExecutorTaskMapper scheduledExecutorTaskMapper;
	
	@Override
	public List<ScheduledExecutorTask> findAllClazzNotNull(){
		List<ScheduledExecutorTask> tasks = scheduledExecutorTaskMapper.findAllClazzNotNull();
		return tasks;
	}

	@Override
	public ScheduledExecutorTask findById(Integer id) {
		ScheduledExecutorTask task = scheduledExecutorTaskMapper.findById(id);
		return task;
	}
	
}
