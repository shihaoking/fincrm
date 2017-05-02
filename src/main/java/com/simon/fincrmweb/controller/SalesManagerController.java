/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrmweb.controller;

import com.alibaba.druid.util.StringUtils;
import com.simon.fincrmweb.service.facade.IUserInfo;
import com.simon.fincrmweb.service.facade.IUserLevel;
import com.simon.fincrmprod.service.facade.enums.UserLevelEnum;
import com.simon.fincrmprod.service.facade.model.UserInfoModel;
import com.simon.fincrmprod.service.facade.model.UserLevelModel;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.CommonResult;
import com.simon.fincrmprod.service.facade.result.SalesmanInfoWithManagerResult;
import com.simon.fincrmprod.service.facade.result.UserInfoQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author jinshihao
 * @version $Id: ManagerController.java, v 0.1 2016-09-27 15:03 jinshihao Exp $$
 */
@Controller
@RequestMapping("/salesmanager")
public class SalesManagerController extends UserInfoBaseController {
    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;


    @RequestMapping("/list")
    public String getList(ModelMap modelMap, @RequestParam(name = "name", required =  false) String searchName, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) {

        UserInfoQueryResult result;

        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setPageNum(pageNum);
        request.setPageSize(20);

        if(StringUtils.isEmpty(searchName)) {
            request.setId(UserLevelEnum.ROLE_SALESMANAGER.getLeveId());
            result = userInfo.selectByLevelId(request);
        }else{
            request.setId(UserLevelEnum.ROLE_SALESMANAGER.getLeveId());
            request.setName(searchName);
            result = userInfo.selectByLevelIdAndName(request);
        }

        modelMap.addAttribute("pageInfo", result.getPageInfo());

        modelMap.addAttribute("managerList", result.getUserInfoModelList());
        return "salesmanager/list";
    }

    @RequestMapping(value = "/getSalesManagerInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfoModel getSalesmanInfo(int id) {
        UserInfoModel result = userInfo.selectByPrimaryKey(id);
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody SalesmanInfoWithManagerResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            UserInfoModel userInfoDo = new UserInfoModel();
            userInfoDo.setId(info.getSalesmanId());
            userInfoDo.setUserName(info.getUserName());
            userInfoDo.setPhonenumber(info.getPhonenumber());
            userInfoDo.setEmail(info.getEmail());
            userInfoDo.setUserPwd(info.getUserPwd());
            userInfoDo.setAddress(info.getAddress());
            userInfoDo.setBirthday(info.getBirthday());
            userInfoDo.setGender(info.getGender());
            userInfoDo.setStatus(info.getStatus());

            userInfo.updateByPrimaryKeySelective(userInfoDo);

        } catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody SalesmanInfoWithManagerResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            UserInfoModel userInfoDo = new UserInfoModel();
            userInfoDo.setUserName(info.getUserName());
            userInfoDo.setPhonenumber(info.getPhonenumber());
            userInfoDo.setEmail(info.getEmail());
            userInfoDo.setUserPwd(info.getUserPwd());
            userInfoDo.setAddress(info.getAddress());
            userInfoDo.setBirthday(info.getBirthday());
            userInfoDo.setGender(info.getGender());
            userInfoDo.setStatus(info.getStatus());
            userInfoDo.setCreateTime(new Date());
            userInfo.insertSelective(userInfoDo);

            UserLevelModel userLevelDo = new UserLevelModel();
            userLevelDo.setLevelId(UserLevelEnum.ROLE_SALESMANAGER.getLeveId());
            userLevelDo.setUserId(userInfoDo.getId());
            userLevel.insert(userLevelDo);

        } catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }
}
