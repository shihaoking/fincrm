package com.simon.fincrmweb.service.impl;


import com.simon.fincrmweb.service.facade.ICustomerInfo;
import com.simon.fincrmprod.service.facade.api.CustomerInfoFacade;
import com.simon.fincrmprod.service.facade.model.CustomerInfoModel;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.CustomerInfoQueryResult;
import com.simon.fincrmprod.service.facade.result.CustomerInfoWithSalesmanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinshihao on 16/8/24.
 */
@Service
public class CustomerInfoImpl implements ICustomerInfo {

    @Autowired
    private CustomerInfoFacade customerInfoFacade;

    public int deleteByPrimaryKey(Integer id) {
        return customerInfoFacade.deleteByPrimaryKey(id);
    }

    public int insert(CustomerInfoModel record) {
        return customerInfoFacade.insert(record);
    }

    public int insertSelective(CustomerInfoModel record) {
        return customerInfoFacade.insertSelective(record);
    }

    public CustomerInfoModel selectByPrimaryKey(Integer id) {
        return customerInfoFacade.selectByPrimaryKey(id);
    }

    public CustomerInfoQueryResult getBySalesmanId(CommonInfoQueryRequest request) {
        return customerInfoFacade.getBySalesmanId(request);
    }

    public CustomerInfoQueryResult getBySalesmanIdAndCustomerName(CommonInfoQueryRequest request) {
        return customerInfoFacade.getBySalesmanIdAndCustomerName(request);
    }

    public CustomerInfoQueryResult getByManagerIdAndCustomerName(CommonInfoQueryRequest request) {
        return customerInfoFacade.getByManagerIdAndCustomerName(request);
    }

    public CustomerInfoQueryResult getByManagerId(CommonInfoQueryRequest request) {
        return customerInfoFacade.getByManagerId(request);
    }

    public CustomerInfoQueryResult selectAll(CommonInfoQueryRequest request){
        return  customerInfoFacade.selectAll(request);
    }

    public int updateByPrimaryKeySelective(CustomerInfoModel record) {
        return customerInfoFacade.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerInfoModel record) {
        return customerInfoFacade.updateByPrimaryKey(record);
    }

    public CustomerInfoWithSalesmanResult getCustomerInfoWithSalesman(Integer customerId) {
        return customerInfoFacade.getCustomerInfoWithSalesman(customerId);
    }


}
