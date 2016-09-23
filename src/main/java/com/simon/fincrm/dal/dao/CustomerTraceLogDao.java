package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.CustomerTraceLogDo;
import com.simon.fincrm.dal.model.SearchWithIdAndNameRequest;

import java.util.List;

public interface CustomerTraceLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerTraceLogDo record);

    int insertSelective(CustomerTraceLogDo record);

    CustomerTraceLogDo selectByPrimaryKey(Integer id);

    List<CustomerTraceLogDo> selectByCustomerId(Integer customerId);

    List<CustomerTraceLogDo> selectBySalesmanId(Integer salesmanId);

    List<CustomerTraceLogDo> selectByManagerId(Integer managerId);

    int updateByPrimaryKeySelective(CustomerTraceLogDo record);

    int updateByPrimaryKey(CustomerTraceLogDo record);
}