package org.feh.dao;

import java.util.List;

import org.feh.model.TimerTaskModel;

public interface TimerTaskModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimerTaskModel record);

    int insertSelective(TimerTaskModel record);

    TimerTaskModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimerTaskModel record);

    int updateByPrimaryKey(TimerTaskModel record);

	List<TimerTaskModel> findAllClazzNotNull();
}