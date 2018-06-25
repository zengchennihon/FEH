package org.feh.dao;

import org.feh.model.Skill;

public interface SkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKeyWithBLOBs(Skill record);

    int updateByPrimaryKey(Skill record);
}