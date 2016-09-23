package com.simon.fincrm.dal.dao;


import com.simon.fincrm.dal.model.SearchWithIdAndNameRequest;
import com.simon.fincrm.dal.model.UserInfoDo;
import java.util.List;

public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.UserInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.UserInfoDo record);

    UserInfoDo selectByPrimaryKey(Integer id);

    UserInfoDo selectByName(String name);

    List<UserInfoDo> selectAll(Boolean status);

    List<UserInfoDo> selectByManageId(Integer managerId);

    List<UserInfoDo> selectByLevelId(Integer levelId);

    List<UserInfoDo> selectByManageIdAndSalesmanName(SearchWithIdAndNameRequest request);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.UserInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.UserInfoDo record);
}