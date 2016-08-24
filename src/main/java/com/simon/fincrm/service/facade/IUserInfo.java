package com.simon.fincrm.service.facade;

import com.simon.fincrm.dal.model.UserInfoDo;

import java.util.List;

/**
 * Created by jinshihao on 16/8/24.
 */
public interface IUserInfo {
    int deleteByPrimaryKey(Integer id);

    int insert(com.simon.fincrm.dal.model.UserInfoDo record);

    int insertSelective(com.simon.fincrm.dal.model.UserInfoDo record);

    UserInfoDo selectByPrimaryKey(Integer id);

    List<UserInfoDo> selectAll(Boolean status);

    int updateByPrimaryKeySelective(com.simon.fincrm.dal.model.UserInfoDo record);

    int updateByPrimaryKey(com.simon.fincrm.dal.model.UserInfoDo record);
}
