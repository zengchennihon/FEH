package org.feh.dao;

import org.feh.model.SkillName;

public interface SkillNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkillName record);

    int insertSelective(SkillName record);

    SkillName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkillName record);

    int updateByPrimaryKey(SkillName record);
}