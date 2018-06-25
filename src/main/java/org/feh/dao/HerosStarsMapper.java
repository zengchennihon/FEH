package org.feh.dao;

import org.feh.model.HerosStars;

public interface HerosStarsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HerosStars record);

    int insertSelective(HerosStars record);

    HerosStars selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HerosStars record);

    int updateByPrimaryKey(HerosStars record);
}