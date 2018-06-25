package org.feh.dao;

import org.feh.model.SkillDescription;

public interface SkillDescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkillDescription record);

    int insertSelective(SkillDescription record);

    SkillDescription selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkillDescription record);

    int updateByPrimaryKey(SkillDescription record);
}