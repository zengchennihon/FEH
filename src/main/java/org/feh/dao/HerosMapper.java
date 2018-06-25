package org.feh.dao;

import org.feh.model.Heros;

public interface HerosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Heros record);

    int insertSelective(Heros record);

    Heros selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Heros record);

    int updateByPrimaryKeyWithBLOBs(Heros record);

    int updateByPrimaryKey(Heros record);
}