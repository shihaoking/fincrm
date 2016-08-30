package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.CustomerInfoDao;
import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.simon.fincrm.service.facade.ICustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jinshihao on 16/8/24.
 */
@Service
public class CustomerInfoImpl implements ICustomerInfo {

    @Autowired
    private CustomerInfoDao customerInfoDao;

    public int deleteByPrimaryKey(Integer id) {
        return customerInfoDao.deleteByPrimaryKey(id);
    }

    public int insert(CustomerInfoDo record) {
        return customerInfoDao.insert(record);
    }

    public int insertSelective(CustomerInfoDo record) {
        return customerInfoDao.insertSelective(record);
    }

    public CustomerInfoDo selectByPrimaryKey(Integer id) {
        return customerInfoDao.selectByPrimaryKey(id);
    }

    public List<CustomerInfoDo> getByCustomerId(Integer customerId) {
        return customerInfoDao.getByCustomerId(customerId);
    }

    public int updateByPrimaryKeySelective(CustomerInfoDo record) {
        return customerInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerInfoDo record) {
        return customerInfoDao.updateByPrimaryKey(record);
    }
}
