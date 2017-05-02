package com.simon.fincrmweb.service.facade;

import com.simon.fincrmprod.service.facade.model.SalesmanCustomerCountModel;
import com.simon.fincrmprod.service.facade.model.SalesmanCustomerRelationModel;

import java.util.List;

/**
 * Created by jinshihao on 16/8/30.
 */
public interface ISalesmanCustomerRelation {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanCustomerRelationModel record);

    int insertSelective(SalesmanCustomerRelationModel record);

    SalesmanCustomerRelationModel selectByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(SalesmanCustomerRelationModel record);

    int updateByPrimaryKey(SalesmanCustomerRelationModel record);

    List<SalesmanCustomerCountModel> selectCustomerCountBySalesmanIds(String ids);
}
