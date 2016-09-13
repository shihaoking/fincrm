package com.simon.fincrm.dal.dao;

import com.simon.fincrm.dal.model.SalesmanManagerReationDo;

public interface SalesmanManagerReationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanManagerReationDo record);

    int insertSelective(SalesmanManagerReationDo record);

    SalesmanManagerReationDo selectByPrimaryKey(Integer id);

    SalesmanManagerReationDo selectBySalesmanId(Integer id);

    int updateByPrimaryKeySelective(SalesmanManagerReationDo record);

    int updateByPrimaryKey(SalesmanManagerReationDo record);
}