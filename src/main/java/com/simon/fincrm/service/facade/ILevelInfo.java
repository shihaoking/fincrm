package com.simon.fincrm.service.facade;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface ILevelInfo {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.LevelInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.LevelInfoDo record);

    com.simon.fincrm.dal.model.LevelInfoDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.LevelInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.LevelInfoDo record);
}
