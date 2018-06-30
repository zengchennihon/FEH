package org.feh.dao;

import org.feh.model.HeroName;

public interface HeroNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeroName record);

    int insertSelective(HeroName record);

    HeroName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeroName record);

    int updateByPrimaryKey(HeroName record);

	HeroName findByHeroId(Integer heroId);
}