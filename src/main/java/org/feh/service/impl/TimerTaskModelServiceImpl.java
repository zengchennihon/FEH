package org.feh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.feh.dao.TimerTaskModelMapper;
import org.feh.model.TimerTaskModel;
import org.feh.service.TimerTaskModelService;
import org.springframework.stereotype.Service;

@Service
public class TimerTaskModelServiceImpl implements TimerTaskModelService {
	
	@Resource
	private TimerTaskModelMapper modelMapper;
	
	@Override
	public List<TimerTaskModel> findAllClazzNotNull(){
		List<TimerTaskModel> taskModels = modelMapper.findAllClazzNotNull();
		return taskModels;
	}
	
}
