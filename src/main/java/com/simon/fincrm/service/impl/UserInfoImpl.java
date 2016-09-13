package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.SalesmanManagerReationDao;
import com.simon.fincrm.dal.dao.UserInfoDao;
import com.simon.fincrm.dal.model.SalesmanManagerReationDo;
import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.result.SalesmanInfoWithManagerResult;
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

    @Autowired
    private SalesmanManagerReationDao salesmanManagerReationDao;

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

    public List<UserInfoDo> selectByManageId(Integer managerId) {
        return userInfoDao.selectByManageId(managerId);
    }

    public List<UserInfoDo> selectByLevelId(Integer levelId) {
        return userInfoDao.selectByLevelId(levelId);
    }

    public int updateByPrimaryKeySelective(UserInfoDo record) {
        return userInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserInfoDo record) {
        return userInfoDao.updateByPrimaryKey(record);
    }

    public SalesmanInfoWithManagerResult getSalesmanInfoWithManager(Integer salesmanId) {
        SalesmanInfoWithManagerResult result = new SalesmanInfoWithManagerResult();

        try {
            UserInfoDo salesmanBaseInfo = userInfoDao.selectByPrimaryKey(salesmanId);

            result.setSalesmanId(salesmanBaseInfo.getId());
            result.setUserName(salesmanBaseInfo.getUserName());
            result.setEmail(salesmanBaseInfo.getEmail());
            result.setPhonenumber(salesmanBaseInfo.getPhonenumber());
            result.setGender(salesmanBaseInfo.getGender());
            result.setAddress(salesmanBaseInfo.getAddress());
            result.setBirthday(salesmanBaseInfo.getBirthday());
            result.setUserPwd(salesmanBaseInfo.getUserPwd());
            result.setStatus(salesmanBaseInfo.getStatus());
            result.setCreateTime(salesmanBaseInfo.getCreateTime());

            SalesmanManagerReationDo salesmanManagerReationDo = salesmanManagerReationDao.selectBySalesmanId(salesmanId);

            result.setManagerId(salesmanManagerReationDo.getManagerId());
        }catch (Exception ex){

        }

        return result;
    }
}
