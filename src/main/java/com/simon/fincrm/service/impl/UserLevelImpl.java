/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.dao.UserLevelDao;
import com.simon.fincrm.dal.model.UserLevelDo;
import com.simon.fincrm.service.facade.IUserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinshihao
 * @version $Id: UserLevelImpl.java, v 0.1 2016-09-13 19:10 jinshihao Exp $$
 */
@Service
public class UserLevelImpl implements IUserLevel {
    @Autowired
    private UserLevelDao userLevelDao;

    public int deleteByPrimaryKey(Integer id) {
        return userLevelDao.deleteByPrimaryKey(id);
    }

    public int insert(UserLevelDo record) {
        return userLevelDao.insert(record);
    }

    public int insertSelective(UserLevelDo record) {
        return userLevelDao.insertSelective(record);
    }

    public UserLevelDo selectByPrimaryKey(Integer id) {
        return userLevelDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserLevelDo record) {
        return userLevelDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserLevelDo record) {
        return userLevelDao.updateByPrimaryKey(record);
    }
}
