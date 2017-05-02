package com.simon.fincrmweb.service.impl;


import com.simon.fincrmweb.service.facade.ISalesmanCustomerRelation;
import com.simon.fincrmprod.service.facade.api.SalesmanCustomerRelationFacade;
import com.simon.fincrmprod.service.facade.model.SalesmanCustomerCountModel;
import com.simon.fincrmprod.service.facade.model.SalesmanCustomerRelationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jinshihao on 16/8/30.
 */
@Service
public class SalesmanCustomerRelationImpl implements ISalesmanCustomerRelation {
    @Autowired
    private SalesmanCustomerRelationFacade salesmanCustomerRelationFacade;

    public int deleteByPrimaryKey(Integer id) {
        return salesmanCustomerRelationFacade.deleteByPrimaryKey(id);
    }

    public int insert(SalesmanCustomerRelationModel record) {
        return salesmanCustomerRelationFacade.insert(record);
    }

    public int insertSelective(SalesmanCustomerRelationModel record) {
        return salesmanCustomerRelationFacade.insertSelective(record);
    }

    public SalesmanCustomerRelationModel selectByCustomerId(Integer customerId) {
        return salesmanCustomerRelationFacade.selectByCustomerId(customerId);
    }

    public int updateByPrimaryKeySelective(SalesmanCustomerRelationModel record) {
        return salesmanCustomerRelationFacade.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SalesmanCustomerRelationModel record) {
        return salesmanCustomerRelationFacade.updateByPrimaryKey(record);
    }

    public List<SalesmanCustomerCountModel> selectCustomerCountBySalesmanIds(String ids) {
        return salesmanCustomerRelationFacade.selectCustomerCountBySalesmanIds(ids);
    }
}
