/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.facade;


import com.simon.fincrmprod.service.facade.model.SalesmanManagerRelationModel;

/**
 * @author jinshihao
 * @version $Id: ISalesmanManagerReation.java, v 0.1 2016-09-13 16:13 jinshihao Exp $$
 */
public interface ISalesmanManagerReation {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanManagerRelationModel record);

    int insertSelective(SalesmanManagerRelationModel record);

    SalesmanManagerRelationModel selectByPrimaryKey(Integer id);

    SalesmanManagerRelationModel selectBySalesmanId(Integer id);

    int updateByPrimaryKeySelective(SalesmanManagerRelationModel record);

    int updateByPrimaryKey(SalesmanManagerRelationModel record);
}
