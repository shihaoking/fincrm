package com.simon.fincrmweb.controller;

import com.alibaba.druid.util.StringUtils;
import com.simon.fincrmweb.service.UserSecurityUtils;
import com.simon.fincrmweb.service.entities.LoginUserInfo;
import com.simon.fincrmweb.service.facade.ICustomerInfo;
import com.simon.fincrmweb.service.facade.ISalesmanCustomerRelation;
import com.simon.fincrmweb.service.facade.IUserInfo;
import com.simon.fincrmweb.service.facade.IUserLevel;
import com.simon.fincrmweb.service.util.CommonCacheKey;
import com.simon.fincrmprod.service.facade.enums.UserLevelEnum;
import com.simon.fincrmprod.service.facade.model.*;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.CommonResult;
import com.simon.fincrmprod.service.facade.result.CustomerInfoQueryResult;
import com.simon.fincrmprod.service.facade.result.CustomerInfoWithSalesmanResult;
import com.simon.fincrmprod.service.facade.result.UserInfoQueryResult;
import com.simon.fincrmweb.service.util.LogFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    Logger LOGGER = LoggerFactory.getLogger(LogFileUtil.CONTROLLER);

    @Autowired
    private ISalesmanCustomerRelation salesmanCustomerRelation;

    @Autowired
    private ICustomerInfo customerInfo;

    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id", required = false, defaultValue = "-1") int id, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) throws IOException {
        CustomerInfoQueryResult result;
        LoginUserInfo loginLoginUserInfo = UserSecurityUtils.getCurrentUser();

        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setPageNum(pageNum);
        request.setPageSize(20);

        if (id != -1) {
            request.setId(id);

            if (StringUtils.isEmpty(name)) {
                result = customerInfo.getBySalesmanId(request);
            } else {
                request.setName(name);
                result = customerInfo.getBySalesmanIdAndCustomerName(request);
            }

        } else {
            if (StringUtils.isEmpty(name)) {
                if (UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_SALESMANAGER.name())) {
                    request.setId(loginLoginUserInfo.getUserId());
                    result = customerInfo.getByManagerId(request);
                } else {
                    request.setId(loginLoginUserInfo.getUserId());
                    result = customerInfo.getBySalesmanId(request);
                }
            } else {
                request.setId(loginLoginUserInfo.getUserId());
                request.setName(name);

                if (UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_SALESMANAGER.name())) {
                    result = customerInfo.getByManagerIdAndCustomerName(request);
                } else {
                    result = customerInfo.getBySalesmanIdAndCustomerName(request);
                }
            }
        }

        List<CustomerInfoModel> customerInfoModels = result.getCustomerInfoModelList();
        PageInfo pageInfo = result.getPageInfo();

        modelMap.addAttribute("pageInfo", pageInfo);
        modelMap.addAttribute("customerList", customerInfoModels);

        CommonInfoQueryRequest salesmaQrequest = new CommonInfoQueryRequest();
        salesmaQrequest.setId(loginLoginUserInfo.getUserId());

        UserInfoQueryResult salesmanList = userInfo.selectByManageId(salesmaQrequest);

        modelMap.addAttribute("salesmanList", salesmanList.getUserInfoModelList());
        modelMap.addAttribute("requestSalesmanId", id);

        return "customer/list";
    }

    @RequestMapping(value = "getCountBySalesmanIds", method = RequestMethod.GET)
    @ResponseBody
    public List<SalesmanCustomerCountModel> getListByIds(@RequestParam(name = "ids") String ids) {
        String cacheKey = String.format(CommonCacheKey.CUSTOMER_COUNT_BY_SALESMANID_CACHE_KEY, ids);
        List<SalesmanCustomerCountModel> result;

        result = salesmanCustomerRelation.selectCustomerCountBySalesmanIds(ids);
        if (result == null) {
            return new ArrayList<SalesmanCustomerCountModel>();
        }

        return result;
    }

    @RequestMapping(value = "getCustomerInfo", method = RequestMethod.GET)
    @ResponseBody
    public CustomerInfoWithSalesmanResult getCusomterInfo(@RequestParam(name = "id") int id) {
        CustomerInfoWithSalesmanResult reuslt = customerInfo.getCustomerInfoWithSalesman(id);

        return reuslt;
    }

    @RequestMapping(value = "getBySalesmanId", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerInfoModel> getBySalesmanId(@RequestParam("id") int id) {
        UserLevelModel userLevelDo = userLevel.selectByUserId(id);


        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setId(id);

        CustomerInfoQueryResult result;
        if (userLevelDo.getLevelId().equals(UserLevelEnum.ROLE_SALESMANAGER.getLeveId())){
            result = customerInfo.getByManagerId(request);
        }else {
            result = customerInfo.getBySalesmanId(request);
        }

        return result.getCustomerInfoModelList();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody CustomerInfoWithSalesmanResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            CustomerInfoModel CustomerInfoModel = customerInfo.selectByPrimaryKey(info.getCustomerId());
            CustomerInfoModel.setId(info.getCustomerId());
            CustomerInfoModel.setCustomerName(info.getCustomerName());
            CustomerInfoModel.setPhoneNumber(info.getPhoneNumber());
            CustomerInfoModel.setEmail(info.getEmail());
            CustomerInfoModel.setStatus(info.getStatus());
            customerInfo.updateByPrimaryKeySelective(CustomerInfoModel);

            SalesmanCustomerRelationModel salesmanCustomerRelationModel = salesmanCustomerRelation.selectByCustomerId(info.getCustomerId());
            salesmanCustomerRelationModel.setSalesmanId(info.getSalesmanId());
            salesmanCustomerRelation.updateByPrimaryKeySelective(salesmanCustomerRelationModel);
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
    public CommonResult add(@RequestBody CustomerInfoWithSalesmanResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            CustomerInfoModel CustomerInfoModel = new CustomerInfoModel();
            CustomerInfoModel.setCustomerName(info.getCustomerName());
            CustomerInfoModel.setPhoneNumber(info.getPhoneNumber());
            CustomerInfoModel.setEmail(info.getEmail());
            CustomerInfoModel.setStatus(info.getStatus());
            CustomerInfoModel.setCreateTime(new Date());
            CustomerInfoModel.setCreator(UserSecurityUtils.getCurrentUserId());
            customerInfo.insert(CustomerInfoModel);

            SalesmanCustomerRelationModel salesmanCustomerRelationModel = new SalesmanCustomerRelationModel();
            salesmanCustomerRelationModel.setSalesmanId(info.getSalesmanId());
            salesmanCustomerRelationModel.setCustomerId(CustomerInfoModel.getId());
            salesmanCustomerRelation.insert(salesmanCustomerRelationModel);

        } catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("id") int id) {
        CommonResult commonResult = new CommonResult();

        try {
            CustomerInfoModel CustomerInfoModel = customerInfo.selectByPrimaryKey(id);
            CustomerInfoModel.setStatus(false);

            customerInfo.updateByPrimaryKeySelective(CustomerInfoModel);

        } catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }
}
