package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.UserInfoDao;
import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.service.facade.IUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jinshihao on 16/8/24.
 */
@Service
public class UserInfoImpl implements IUserInfo {
    @Autowired
    private UserInfoDao userInfoDao;

    public int deleteByPrimaryKey(Integer id) {
        return userInfoDao.deleteByPrimaryKey(id);
    }

    public int insert(UserInfoDo record) {
        return userInfoDao.insert(record);
    }

    public int insertSelective(UserInfoDo record) {
        return userInfoDao.insertSelective(record);
    }

    public UserInfoDo selectByPrimaryKey(Integer id) {
        return userInfoDao.selectByPrimaryKey(id);
    }

    public List<UserInfoDo> selectAll(Boolean status) {
        return userInfoDao.selectAll(status);
    }

    public int updateByPrimaryKeySelective(UserInfoDo record) {
        return userInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserInfoDo record) {
        return userInfoDao.updateByPrimaryKey(record);
    }
}
