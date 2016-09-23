package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.CustomerInfoDao;
import com.simon.fincrm.dal.dao.SalesmanCustomerRelationDao;
import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.simon.fincrm.dal.model.CustomerTraceLogDo;
import com.simon.fincrm.dal.model.SalesmanCustomerRelationDo;
import com.simon.fincrm.dal.model.SearchWithIdAndNameRequest;
import com.simon.fincrm.service.facade.ICustomerInfo;
import com.simon.fincrm.service.result.CustomerInfoWithSalesmanResult;
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

    @Autowired
    private SalesmanCustomerRelationDao salesmanCustomerRelationDao;

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

    public List<CustomerInfoDo> getBySalesmanId(Integer customerId) {
        return customerInfoDao.getBySalesmanId(customerId);
    }

    public List<CustomerInfoDo> getBySalesmanIdAndCustomerName(SearchWithIdAndNameRequest request) {
        return customerInfoDao.getBySalesmanIdAndCustomerName(request);
    }

    public List<CustomerInfoDo> getByManagerIdAndCustomerName(SearchWithIdAndNameRequest request) {
        return customerInfoDao.getByManagerIdAndCustomerName(request);
    }

    public List<CustomerInfoDo> getByManagerId(Integer id) {
        return customerInfoDao.getByManagerId(id);
    }

    public List<CustomerInfoDo> selectAll(Boolean status){
        return  customerInfoDao.selectAll(status);
    }

    public int updateByPrimaryKeySelective(CustomerInfoDo record) {
        return customerInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerInfoDo record) {
        return customerInfoDao.updateByPrimaryKey(record);
    }

    public CustomerInfoWithSalesmanResult getCustomerInfoWithSalesman(Integer customerId) {
        CustomerInfoDo customerInfoDo = customerInfoDao.selectByPrimaryKey(customerId);
        SalesmanCustomerRelationDo salesmanCustomerRelationDo = salesmanCustomerRelationDao.selectByCustomerId(customerId);

        CustomerInfoWithSalesmanResult result = new CustomerInfoWithSalesmanResult();
        result.setCustomerId(customerInfoDo.getId());
        result.setCustomerName(customerInfoDo.getCustomerName());
        result.setPhoneNumber(customerInfoDo.getPhoneNumber());
        result.setEmail(customerInfoDo.getEmail());
        result.setCreator(customerInfoDo.getCreator());
        result.setCreateTime(customerInfoDo.getCreateTime());
        result.setStatus(customerInfoDo.getStatus());

        if(salesmanCustomerRelationDo != null) {
            result.setSalesmanId(salesmanCustomerRelationDo.getSalesmanId());
        }else {
            result.setSalesmanId(-1);
        }

        return result;
    }


}
