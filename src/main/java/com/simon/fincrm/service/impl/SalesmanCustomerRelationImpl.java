package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.SalesmanCustomerRelationDao;
import com.simon.fincrm.dal.model.SalesmanCustomerCountDo;
import com.simon.fincrm.dal.model.SalesmanCustomerRelationDo;
import com.simon.fincrm.service.facade.ISalesmanCustomerRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jinshihao on 16/8/30.
 */
@Service
public class SalesmanCustomerRelationImpl implements ISalesmanCustomerRelation {
    @Autowired
    private SalesmanCustomerRelationDao salesmanCustomerReationDao;

    public int deleteByPrimaryKey(Integer id) {
        return salesmanCustomerReationDao.deleteByPrimaryKey(id);
    }

    public int insert(SalesmanCustomerRelationDo record) {
        return salesmanCustomerReationDao.insert(record);
    }

    public int insertSelective(SalesmanCustomerRelationDo record) {
        return salesmanCustomerReationDao.insertSelective(record);
    }

    public SalesmanCustomerRelationDo selectByPrimaryKey(Integer id) {
        return salesmanCustomerReationDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SalesmanCustomerRelationDo record) {
        return salesmanCustomerReationDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SalesmanCustomerRelationDo record) {
        return salesmanCustomerReationDao.updateByPrimaryKey(record);
    }

    public List<SalesmanCustomerCountDo> selectCustomerCountBySalesmanIds(String ids) {
        if(ids == null || ids.equals("")){
            return  null;
        }

        String[] idArray = ids.split(",");
        return salesmanCustomerReationDao.selectCustomerCountBySalesmanIds(idArray);
    }
}
