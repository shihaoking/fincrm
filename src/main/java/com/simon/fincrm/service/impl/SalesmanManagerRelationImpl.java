/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.impl;

import com.simon.fincrm.service.facade.ISalesmanManagerReation;
import com.simon.fincrmprod.service.facade.api.SalesmanManagerReationFacade;
import com.simon.fincrmprod.service.facade.model.SalesmanManagerRelationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinshihao
 * @version $Id: SalesmanManagerRelationImpl.java, v 0.1 2016-09-13 16:13 jinshihao Exp $$
 */
@Service
public class SalesmanManagerRelationImpl implements ISalesmanManagerReation {

    @Autowired
    private SalesmanManagerReationFacade salesmanManagerReationFacade;

    public int deleteByPrimaryKey(Integer id) {
        return salesmanManagerReationFacade.deleteByPrimaryKey(id);
    }

    public int insert(SalesmanManagerRelationModel record) {
        return salesmanManagerReationFacade.insert(record);
    }

    public int insertSelective(SalesmanManagerRelationModel record) {
        return salesmanManagerReationFacade.insertSelective(record);
    }

    public SalesmanManagerRelationModel selectByPrimaryKey(Integer id) {
        return salesmanManagerReationFacade.selectByPrimaryKey(id);
    }

    public SalesmanManagerRelationModel selectBySalesmanId(Integer id) {
        return salesmanManagerReationFacade.selectBySalesmanId(id);
    }

    public int updateByPrimaryKeySelective(SalesmanManagerRelationModel record) {
        return salesmanManagerReationFacade.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SalesmanManagerRelationModel record) {
        return salesmanManagerReationFacade.updateByPrimaryKey(record);
    }
}
