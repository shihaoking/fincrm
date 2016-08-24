package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.CustomerInfoDo;

public interface CustomerInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    com.simon.fincrm.dal.model.CustomerInfoDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.CustomerInfoDo record);
}