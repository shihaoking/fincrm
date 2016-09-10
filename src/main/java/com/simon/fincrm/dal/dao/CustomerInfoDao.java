package com.simon.fincrm.dal.dao;


import com.simon.fincrm.dal.model.CustomerInfoDo;

import java.util.List;

public interface CustomerInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    CustomerInfoDo selectByPrimaryKey(Integer id);

    List<CustomerInfoDo> getBySalesmanId(Integer id);

    List<CustomerInfoDo> selectAll(Boolean status);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.CustomerInfoDo record);

}