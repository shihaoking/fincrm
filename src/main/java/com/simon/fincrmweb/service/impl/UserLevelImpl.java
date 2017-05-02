/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrmweb.service.impl;

import com.simon.fincrmweb.service.facade.IUserLevel;
import com.simon.fincrmprod.service.facade.api.UserLevelFacade;
import com.simon.fincrmprod.service.facade.model.UserLevelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinshihao
 * @version $Id: UserLevelImpl.java, v 0.1 2016-09-13 19:10 jinshihao Exp $$
 */
@Service
public class UserLevelImpl implements IUserLevel {
    @Autowired
    private UserLevelFacade userLevelFacade;

    public int deleteByPrimaryKey(Integer id) {
        return userLevelFacade.deleteByPrimaryKey(id);
    }

    public int insert(UserLevelModel record) {
        return userLevelFacade.insert(record);
    }

    public int insertSelective(UserLevelModel record) {
        return userLevelFacade.insertSelective(record);
    }

    public UserLevelModel selectByPrimaryKey(Integer id) {
        return userLevelFacade.selectByPrimaryKey(id);
    }

    public UserLevelModel selectByUserId(Integer id) {
        return userLevelFacade.selectByUserId(id);
    }

    public int updateByPrimaryKeySelective(UserLevelModel record) {
        return userLevelFacade.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserLevelModel record) {
        return userLevelFacade.updateByPrimaryKey(record);
    }
}
