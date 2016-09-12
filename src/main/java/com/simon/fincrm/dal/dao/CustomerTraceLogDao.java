package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.CustomerTraceLogDo;

import java.util.List;

public interface CustomerTraceLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerTraceLogDo record);

    int insertSelective(CustomerTraceLogDo record);

    CustomerTraceLogDo selectByPrimaryKey(Integer id);

    List<CustomerTraceLogDo> selectByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(CustomerTraceLogDo record);

    int updateByPrimaryKey(CustomerTraceLogDo record);
}