package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.CustomerReportLogDo;

public interface CustomerReportLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.CustomerReportLogDo record);

    int insertSelective(com.simon.fincrm.dal.model.CustomerReportLogDo record);

    com.simon.fincrm.dal.model.CustomerReportLogDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.CustomerReportLogDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.CustomerReportLogDo record);
}