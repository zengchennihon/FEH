package org.feh.dao;

import java.util.List;

import org.feh.model.Hero;
import org.feh.model.vo.HeroAllInfoVo;

public interface HeroMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hero record);

    int insertSelective(Hero record);

    Hero selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hero record);

    int updateByPrimaryKeyWithBLOBs(Hero record);

    int updateByPrimaryKey(Hero record);
    
    List<Hero> findAll();

	List<HeroAllInfoVo> findAllInfoVos();
}