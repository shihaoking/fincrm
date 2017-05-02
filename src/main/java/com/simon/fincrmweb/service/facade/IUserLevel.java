package com.simon.fincrmweb.service.facade;


import com.simon.fincrmprod.service.facade.model.UserLevelModel;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface IUserLevel {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLevelModel record);

    int insertSelective(UserLevelModel record);

    UserLevelModel selectByPrimaryKey(Integer id);

    UserLevelModel selectByUserId(Integer id);

    int updateByPrimaryKeySelective(UserLevelModel record);

    int updateByPrimaryKey(UserLevelModel record);
}
