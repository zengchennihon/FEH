package org.feh.dao;

import java.util.List;

import org.feh.model.ScheduledExecutorTask;

public interface ScheduledExecutorTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScheduledExecutorTask record);

    int insertSelective(ScheduledExecutorTask record);

    ScheduledExecutorTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduledExecutorTask record);

    int updateByPrimaryKey(ScheduledExecutorTask record);

	List<ScheduledExecutorTask> findAllClazzNotNull();
}