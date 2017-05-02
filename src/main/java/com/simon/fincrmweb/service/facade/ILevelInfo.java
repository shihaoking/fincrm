package com.simon.fincrmweb.service.facade;

import com.simon.fincrmprod.service.facade.model.LevelInfoModel;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface ILevelInfo {
    int deleteByPrimaryKey(Integer id);

    int insert(LevelInfoModel record);

    int insertSelective(LevelInfoModel record);

    LevelInfoModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LevelInfoModel record);

    int updateByPrimaryKey(LevelInfoModel record);
}
