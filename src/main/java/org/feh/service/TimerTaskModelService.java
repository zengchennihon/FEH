package org.feh.service;

import java.util.List;

import org.feh.model.TimerTaskModel;

public interface TimerTaskModelService {
	
	public List<TimerTaskModel> findAllClazzNotNull();
	
}
