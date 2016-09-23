package com.simon.fincrm.service.facade;

import com.simon.fincrm.dal.model.CustomerTraceLogDo;

import java.util.List;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface ICustomerTraceLog {
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
