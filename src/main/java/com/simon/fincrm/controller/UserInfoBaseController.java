/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.controller;

import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrmprod.service.facade.model.UserInfoModel;
import com.simon.fincrmprod.service.facade.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jinshihao
 * @version $Id: UserInfoBaseController.java, v 0.1 2016-09-27 15:34 jinshihao Exp $$
 */

public class UserInfoBaseController {
    @Autowired
    private IUserInfo userInfo;

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("id") int id) {
        CommonResult commonResult = new CommonResult();

        try {
            UserInfoModel userInfoDo = userInfo.selectByPrimaryKey(id);
            userInfoDo.setStatus(false);

            userInfo.updateByPrimaryKeySelective(userInfoDo);

        } catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }
}
