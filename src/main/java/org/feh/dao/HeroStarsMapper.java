package org.feh.dao;

import java.util.List;

import org.feh.model.HeroStars;

public interface HeroStarsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeroStars record);

    int insertSelective(HeroStars record);

    HeroStars selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeroStars record);

    int updateByPrimaryKey(HeroStars record);

	List<HeroStars> findByHeroId(Integer heroId);
}