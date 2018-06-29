package org.feh.dao;

import org.feh.model.HeroDetails;

public interface HeroDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeroDetails record);

    int insertSelective(HeroDetails record);

    HeroDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeroDetails record);

    int updateByPrimaryKey(HeroDetails record);
}