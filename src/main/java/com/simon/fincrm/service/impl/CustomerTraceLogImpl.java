/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.CustomerTraceLogDao;
import com.simon.fincrm.dal.model.CustomerTraceLogDo;
import com.simon.fincrm.service.facade.ICustomerTraceLog;
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
    private CustomerTraceLogDao customerTraceLogDao;

    public int deleteByPrimaryKey(Integer id) {
        return customerTraceLogDao.deleteByPrimaryKey(id);
    }

    public int insert(CustomerTraceLogDo record) {
        return customerTraceLogDao.insert(record);
    }

    public int insertSelective(CustomerTraceLogDo record) {
        return customerTraceLogDao.insertSelective(record);
    }

    public CustomerTraceLogDo selectByPrimaryKey(Integer id) {
        return customerTraceLogDao.selectByPrimaryKey(id);
    }

    public List<CustomerTraceLogDo> selectByCustomerId(Integer customerId) {
        return customerTraceLogDao.selectByCustomerId(customerId);
    }

    public int updateByPrimaryKeySelective(CustomerTraceLogDo record) {
        return customerTraceLogDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerTraceLogDo record) {
        return customerTraceLogDao.updateByPrimaryKey(record);
    }
}
