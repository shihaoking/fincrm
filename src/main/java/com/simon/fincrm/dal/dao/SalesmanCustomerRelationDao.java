package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.SalesmanCustomerCountDo;
import com.simon.fincrm.dal.model.SalesmanCustomerRelationDo;

import java.util.List;

public interface SalesmanCustomerRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanCustomerRelationDo record);

    int insertSelective(SalesmanCustomerRelationDo record);

    SalesmanCustomerRelationDo selectByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(SalesmanCustomerRelationDo record);

    int updateByPrimaryKey(SalesmanCustomerRelationDo record);

    List<SalesmanCustomerCountDo> selectCustomerCountBySalesmanIds(String[] ids);
}