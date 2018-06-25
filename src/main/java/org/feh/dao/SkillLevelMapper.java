package org.feh.dao;

import org.feh.model.SkillLevel;

public interface SkillLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkillLevel record);

    int insertSelective(SkillLevel record);

    SkillLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkillLevel record);

    int updateByPrimaryKey(SkillLevel record);
}