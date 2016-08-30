package com.simon.fincrm.service.facade;

import com.simon.fincrm.dal.dao.CustomerInfoDao;
import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.sun.jdi.IntegerType;

import java.util.List;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface ICustomerInfo {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    com.simon.fincrm.dal.model.CustomerInfoDo selectByPrimaryKey(Integer id);

    List<CustomerInfoDo> getByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.CustomerInfoDo record);
}
