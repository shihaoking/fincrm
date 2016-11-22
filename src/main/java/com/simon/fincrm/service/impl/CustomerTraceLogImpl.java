/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.impl;


import com.simon.fincrm.service.facade.ICustomerTraceLog;
import com.simon.fincrmprod.service.facade.api.CustomerTraceLogFacade;
import com.simon.fincrmprod.service.facade.model.CustomerTraceLogModel;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.CustomerTraceLogQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jinshihao
 * @version $Id: CustomerTraceLogImpl.java, v 0.1 2016-09-12 17:43 jinshihao Exp $$
 */
@Service
public class CustomerTraceLogImpl implements ICustomerTraceLog {

    @Autowired
    private CustomerTraceLogFacade customerTraceLogFacade;

    public int deleteByPrimaryKey(Integer id) {
        return customerTraceLogFacade.deleteByPrimaryKey(id);
    }

    public int insert(CustomerTraceLogModel record) {
        return customerTraceLogFacade.insert(record);
    }

    public int insertSelective(CustomerTraceLogModel record) {
        return customerTraceLogFacade.insertSelective(record);
    }

    public CustomerTraceLogModel selectByPrimaryKey(Integer id) {
        return customerTraceLogFacade.selectByPrimaryKey(id);
    }

    public CustomerTraceLogQueryResult selectByCustomerId(CommonInfoQueryRequest request) {
        return customerTraceLogFacade.selectByCustomerId(request);
    }

    public CustomerTraceLogQueryResult selectBySalesmanId(CommonInfoQueryRequest request) {
        return customerTraceLogFacade.selectBySalesmanId(request);
    }

    public CustomerTraceLogQueryResult selectByManagerId(CommonInfoQueryRequest request) {
        return customerTraceLogFacade.selectByManagerId(request);
    }


    public int updateByPrimaryKeySelective(CustomerTraceLogModel record) {
        return customerTraceLogFacade.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerTraceLogModel record) {
        return customerTraceLogFacade.updateByPrimaryKey(record);
    }
}
