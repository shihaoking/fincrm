package com.simon.fincrmweb.service.impl;


import com.simon.fincrmweb.service.facade.IUserInfo;
import com.simon.fincrmprod.service.facade.api.UserInfoFacade;
import com.simon.fincrmprod.service.facade.model.UserInfoModel;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.SalesmanInfoWithManagerResult;
import com.simon.fincrmprod.service.facade.result.UserInfoQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinshihao on 16/8/24.
 */
@Service
public class UserInfoImpl implements IUserInfo {
    @Autowired
    private UserInfoFacade userInfoFacade;

    public int deleteByPrimaryKey(Integer id) {
        return userInfoFacade.deleteByPrimaryKey(id);
    }

    public int insert(UserInfoModel record) {
        return userInfoFacade.insert(record);
    }

    public int insertSelective(UserInfoModel record) {
        return userInfoFacade.insertSelective(record);
    }

    public UserInfoModel selectByPrimaryKey(Integer id) {
        return userInfoFacade.selectByPrimaryKey(id);
    }

    public UserInfoModel selectByName(String name) {
        return userInfoFacade.selectByName(name);
    }

    public UserInfoQueryResult selectAll(CommonInfoQueryRequest request) {
        return userInfoFacade.selectAll(request);
    }

    public UserInfoQueryResult selectByManageId(CommonInfoQueryRequest request) {
        return userInfoFacade.selectByManageId(request);
    }

    public UserInfoQueryResult selectByLevelId(CommonInfoQueryRequest request) {
        return userInfoFacade.selectByLevelId(request);
    }

    public UserInfoQueryResult selectByManageIdAndSalesmanName(CommonInfoQueryRequest request) {
        return userInfoFacade.selectByManageIdAndSalesmanName(request);
    }

    public UserInfoQueryResult selectByLevelIdAndName(CommonInfoQueryRequest request) {
        return userInfoFacade.selectByLevelIdAndName(request);
    }

    public int updateByPrimaryKeySelective(UserInfoModel record) {
        return userInfoFacade.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserInfoModel record) {
        return userInfoFacade.updateByPrimaryKey(record);
    }

    public SalesmanInfoWithManagerResult getSalesmanInfoWithManager(Integer salesmanId) {
        return userInfoFacade.getSalesmanInfoWithManager(salesmanId);
    }
}
