package org.feh.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.feh.model.HeroDetails;

public interface HeroDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeroDetails record);

    int insertSelective(HeroDetails record);

    HeroDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeroDetails record);

    int updateByPrimaryKey(HeroDetails record);

	List<HeroDetails> findByStarsIdsList(List<Integer> starsIds);

	List<HeroDetails> findByStarsIdsSet(@Param("starsIds") Set<Integer> starsIds);

	int insertByList(List<HeroDetails> insertList);

	int updateByList(List<HeroDetails> updateList);

}