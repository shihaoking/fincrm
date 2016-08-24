package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.LevelInfoDo;

public interface LevelInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.LevelInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.LevelInfoDo record);

    com.simon.fincrm.dal.model.LevelInfoDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.LevelInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.LevelInfoDo record);
}