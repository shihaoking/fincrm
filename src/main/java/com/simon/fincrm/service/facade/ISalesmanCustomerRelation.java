package com.simon.fincrm.service.facade;

import com.simon.fincrm.dal.model.SalesmanCustomerCountDo;
import com.simon.fincrm.dal.model.SalesmanCustomerRelationDo;

import java.util.List;

/**
 * Created by jinshihao on 16/8/30.
 */
public interface ISalesmanCustomerRelation {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanCustomerRelationDo record);

    int insertSelective(SalesmanCustomerRelationDo record);

    SalesmanCustomerRelationDo selectByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(SalesmanCustomerRelationDo record);

    int updateByPrimaryKey(SalesmanCustomerRelationDo record);

    List<SalesmanCustomerCountDo> selectCustomerCountBySalesmanIds(String ids);
}
