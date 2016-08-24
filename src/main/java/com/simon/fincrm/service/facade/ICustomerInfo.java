package com.simon.fincrm.service.facade;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface ICustomerInfo {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    com.simon.fincrm.dal.model.CustomerInfoDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.CustomerInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.CustomerInfoDo record);
}
