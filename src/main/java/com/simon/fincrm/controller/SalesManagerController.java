/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.controller;

import com.alibaba.druid.util.StringUtils;
import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.simon.fincrm.dal.model.SearchWithIdAndNameRequest;
import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.dal.model.UserLevelDo;
import com.simon.fincrm.service.UserSecurityUtils;
import com.simon.fincrm.service.entities.LoginUserInfo;
import com.simon.fincrm.service.enums.UserLevelEnum;
import com.simon.fincrm.service.facade.ISalesmanManagerReation;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.facade.IUserLevel;
import com.simon.fincrm.service.interceptor.PageInterceptor;
import com.simon.fincrm.service.result.CommonResult;
import com.simon.fincrm.service.result.SalesmanInfoWithManagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

        PageInterceptor.startPage(pageNum, 20);

        List<UserInfoDo> result;
        if(StringUtils.isEmpty(searchName)) {
            result = userInfo.selectByLevelId(UserLevelEnum.ROLE_SALESMANAGER.getLeveId());
        }else{
            SearchWithIdAndNameRequest request = new SearchWithIdAndNameRequest();
            request.setId(UserLevelEnum.ROLE_SALESMANAGER.getLeveId());
            request.setName(searchName);
            result = userInfo.selectByLevelIdAndName(request);
        }

        PageInterceptor.Page page = PageInterceptor.endPage();
        modelMap.addAttribute("pageInfo", page);

        modelMap.addAttribute("managerList", result);
        return "salesmanager/list";
    }

    @RequestMapping(value = "/getSalesManagerInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfoDo getSalesmanInfo(int id) {
        UserInfoDo result = userInfo.selectByPrimaryKey(id);
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody SalesmanInfoWithManagerResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            UserInfoDo userInfoDo = new UserInfoDo();
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
            UserInfoDo userInfoDo = new UserInfoDo();
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

            UserLevelDo userLevelDo = new UserLevelDo();
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
