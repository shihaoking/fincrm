package com.simon.fincrm.controller;

import com.alibaba.druid.util.StringUtils;
import com.simon.fincrm.service.UserSecurityUtils;
import com.simon.fincrm.service.entities.LoginUserInfo;
import com.simon.fincrm.service.facade.ISalesmanManagerReation;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.facade.IUserLevel;
import com.simon.fincrmprod.service.facade.enums.UserLevelEnum;
import com.simon.fincrmprod.service.facade.model.SalesmanManagerRelationModel;
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
import java.util.List;


/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController extends UserInfoBaseController {

    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;

    @Autowired
    private ISalesmanManagerReation salesmanManagerReation;

    @RequestMapping("/list")
    public String getList(ModelMap modelMap, @RequestParam(name = "id", required = false, defaultValue = "-1") int id, @RequestParam(name = "name", required = false) String searchName, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) {

        LoginUserInfo loginLoginUserInfo = UserSecurityUtils.getCurrentUser();

        UserInfoQueryResult result;

        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setPageNum(pageNum);
        request.setPageSize(20);

        if (id == -1) {
            request.setId(loginLoginUserInfo.getUserId());
        }

        if (StringUtils.isEmpty(searchName)) {
            result = userInfo.selectByManageId(request);
        } else {
            request.setId(id);
            request.setName(searchName);
            result = userInfo.selectByManageIdAndSalesmanName(request);
        }

        modelMap.addAttribute("pageInfo", result.getPageInfo());

        modelMap.addAttribute("salesmanList", result.getUserInfoModelList());
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
    public List<UserInfoModel> getAllSalesman() {
        LoginUserInfo loginLoginUserInfo = UserSecurityUtils.getCurrentUser();
        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setId(loginLoginUserInfo.getUserId());

        UserInfoQueryResult result = userInfo.selectByManageId(request);
        return result.getUserInfoModelList();
    }

    @RequestMapping(value = "/getAllManager", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfoModel> getAllManager() {
        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setId(UserLevelEnum.ROLE_SALESMANAGER.getLeveId());

        UserInfoQueryResult result = userInfo.selectByLevelId(request);
        return result.getUserInfoModelList();
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

            SalesmanManagerRelationModel salesmanManagerReationDo = salesmanManagerReation.selectBySalesmanId(info.getSalesmanId());
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

            SalesmanManagerRelationModel salesmanManagerReationDo = new SalesmanManagerRelationModel();
            salesmanManagerReationDo.setSalesmanId(userInfoDo.getId());
            salesmanManagerReationDo.setManagerId(info.getManagerId());
            salesmanManagerReation.insertSelective(salesmanManagerReationDo);

            UserLevelModel userLevelDo = new UserLevelModel();
            userLevelDo.setLevelId(UserLevelEnum.ROLE_SALESMAN.getLeveId());
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
