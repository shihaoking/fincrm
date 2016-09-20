package com.simon.fincrm.controller;

import com.simon.fincrm.dal.model.SalesmanManagerReationDo;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController {

    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;

    @Autowired
    private ISalesmanManagerReation salesmanManagerReation;

    @RequestMapping("/list")
    public String getList(ModelMap modelMap, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) {

        LoginUserInfo loginLoginUserInfo = UserSecurityUtils.getCurrentUser();

        PageInterceptor.startPage(pageNum, 20);

        List<UserInfoDo> result = userInfo.selectByManageId(loginLoginUserInfo.getUserId());

        PageInterceptor.Page page = PageInterceptor.endPage();
        modelMap.addAttribute("pageInfo", page);

        modelMap.addAttribute("salesmanList", result);
        return "salesman/list";
    }

    @RequestMapping(value = "/getSalesmanInfo", method = RequestMethod.GET)
    @ResponseBody
    public SalesmanInfoWithManagerResult getSalesmanInfo(int id) {
        SalesmanInfoWithManagerResult result = userInfo.getSalesmanInfoWithManager(id);

        return result;
    }

    @RequestMapping(value = "/getAllSalesman", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfoDo> getAllSalesman() {
        LoginUserInfo loginLoginUserInfo =  UserSecurityUtils.getCurrentUser();
        List<UserInfoDo> result = userInfo.selectByManageId(loginLoginUserInfo.getUserId());
        return result;
    }

    @RequestMapping(value = "/getAllManager", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfoDo> getAllManager() {
        List<UserInfoDo> result = userInfo.selectByLevelId(UserLevelEnum.ROLE_MANAGER.getLeveId());
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

            SalesmanManagerReationDo salesmanManagerReationDo = salesmanManagerReation.selectBySalesmanId(info.getSalesmanId());
            salesmanManagerReationDo.setManagerId(info.getManagerId());
            salesmanManagerReation.updateByPrimaryKey(salesmanManagerReationDo);

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

            SalesmanManagerReationDo salesmanManagerReationDo = new SalesmanManagerReationDo();
            salesmanManagerReationDo.setSalesmanId(userInfoDo.getId());
            salesmanManagerReationDo.setManagerId(info.getManagerId());
            salesmanManagerReation.insertSelective(salesmanManagerReationDo);

            UserLevelDo userLevelDo = new UserLevelDo();
            userLevelDo.setLevelId(1);
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
