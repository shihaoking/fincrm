package com.simon.fincrm.service.facade;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface ICustomerReportLog {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.CustomerReportLogDo record);

    int insertSelective(com.simon.fincrm.dal.model.CustomerReportLogDo record);

    com.simon.fincrm.dal.model.CustomerReportLogDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.CustomerReportLogDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.CustomerReportLogDo record);
}
