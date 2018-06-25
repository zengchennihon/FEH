package org.feh.dao;

import org.feh.model.HerosDetails;

public interface HerosDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HerosDetails record);

    int insertSelective(HerosDetails record);

    HerosDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HerosDetails record);

    int updateByPrimaryKey(HerosDetails record);
}