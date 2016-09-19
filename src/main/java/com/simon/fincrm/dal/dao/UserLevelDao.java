package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.UserLevelDo;

public interface UserLevelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.UserLevelDo record);

    int insertSelective(com.simon.fincrm.dal.model.UserLevelDo record);

    com.simon.fincrm.dal.model.UserLevelDo selectByPrimaryKey(Integer id);

    UserLevelDo selectByUserId(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.UserLevelDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.UserLevelDo record);
}