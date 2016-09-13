/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.facade;

import com.simon.fincrm.dal.model.SalesmanManagerReationDo;

/**
 * @author jinshihao
 * @version $Id: ISalesmanManagerReation.java, v 0.1 2016-09-13 16:13 jinshihao Exp $$
 */
public interface ISalesmanManagerReation {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanManagerReationDo record);

    int insertSelective(SalesmanManagerReationDo record);

    SalesmanManagerReationDo selectByPrimaryKey(Integer id);

    SalesmanManagerReationDo selectBySalesmanId(Integer id);

    int updateByPrimaryKeySelective(SalesmanManagerReationDo record);

    int updateByPrimaryKey(SalesmanManagerReationDo record);
}
