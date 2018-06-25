package org.feh.dao;

import org.feh.model.HerosName;

public interface HerosNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HerosName record);

    int insertSelective(HerosName record);

    HerosName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HerosName record);

    int updateByPrimaryKey(HerosName record);
}