package com.simon.fincrmweb.service.facade;

import com.simon.fincrmprod.service.facade.model.UserInfoModel;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.SalesmanInfoWithManagerResult;
import com.simon.fincrmprod.service.facade.result.UserInfoQueryResult;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface IUserInfo {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoModel record);

    int insertSelective(UserInfoModel record);

    UserInfoModel selectByPrimaryKey(Integer id);

    UserInfoModel selectByName(String name);

    UserInfoQueryResult selectAll(CommonInfoQueryRequest request);

    UserInfoQueryResult selectByManageId(CommonInfoQueryRequest request);

    UserInfoQueryResult selectByLevelId(CommonInfoQueryRequest request);

    UserInfoQueryResult selectByManageIdAndSalesmanName(CommonInfoQueryRequest request);

    UserInfoQueryResult selectByLevelIdAndName(CommonInfoQueryRequest request);

    int updateByPrimaryKeySelective(UserInfoModel record);

    int updateByPrimaryKey(UserInfoModel record);

    SalesmanInfoWithManagerResult getSalesmanInfoWithManager(Integer salesmanId);
}
