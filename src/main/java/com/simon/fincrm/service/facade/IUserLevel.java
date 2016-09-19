package com.simon.fincrm.service.facade;

import com.simon.fincrm.dal.model.UserLevelDo;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface IUserLevel {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.UserLevelDo record);

    int insertSelective(com.simon.fincrm.dal.model.UserLevelDo record);

    UserLevelDo selectByPrimaryKey(Integer id);

    UserLevelDo selectByUserId(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.UserLevelDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.UserLevelDo record);
}
