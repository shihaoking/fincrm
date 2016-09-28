package com.simon.fincrm.controller;

import com.alibaba.druid.util.StringUtils;
import com.simon.fincrm.dal.model.*;
import com.simon.fincrm.service.UserSecurityUtils;
import com.simon.fincrm.service.entities.LoginUserInfo;
import com.simon.fincrm.service.enums.UserLevelEnum;
import com.simon.fincrm.service.facade.ICustomerInfo;
import com.simon.fincrm.service.facade.ISalesmanCustomerRelation;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.facade.IUserLevel;
import com.simon.fincrm.service.interceptor.PageInterceptor;
import com.simon.fincrm.service.result.CommonResult;
import com.simon.fincrm.service.result.CustomerInfoWithSalesmanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private ISalesmanCustomerRelation salesmanCustomerRelation;

    @Autowired
    private ICustomerInfo customerInfo;

    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id", required = false, defaultValue = "-1") int id, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) {
        List<CustomerInfoDo> result = new ArrayList<CustomerInfoDo>();
        LoginUserInfo loginLoginUserInfo = UserSecurityUtils.getCurrentUser();
        PageInterceptor.startPage(pageNum, 20);

        if (id != -1) {
            if(StringUtils.isEmpty(name)) {
                result = customerInfo.getBySalesmanId(id);
            }else {
                SearchWithIdAndNameRequest request = new SearchWithIdAndNameRequest();
                request.setId(id);
                request.setName(name);
                result = customerInfo.getBySalesmanIdAndCustomerName(request);
            }

        } else {

            if(StringUtils.isEmpty(name)) {
                if (UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_SALESMANAGER.name())) {
                    result = customerInfo.getByManagerId(loginLoginUserInfo.getUserId());
                }else{
                    result = customerInfo.getBySalesmanId(loginLoginUserInfo.getUserId());
                }
            }else{
                SearchWithIdAndNameRequest request = new SearchWithIdAndNameRequest();
                request.setId(loginLoginUserInfo.getUserId());
                request.setName(name);

                if (UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_SALESMANAGER.name())) {
                    result = customerInfo.getByManagerIdAndCustomerName(request);
                }else{
                    result = customerInfo.getBySalesmanIdAndCustomerName(request);
                }
            }
        }

        PageInterceptor.Page page = PageInterceptor.endPage();
        modelMap.addAttribute("pageInfo", page);

        modelMap.addAttribute("customerList", result);


        List<UserInfoDo> salesmanList = userInfo.selectByManageId(loginLoginUserInfo.getUserId());

        modelMap.addAttribute("salesmanList", salesmanList);
        modelMap.addAttribute("requestSalesmanId", id);

        return "customer/list";
    }

    @RequestMapping(value = "getCountBySalesmanIds", method = RequestMethod.GET)
    @ResponseBody
    public List<SalesmanCustomerCountDo> getListByIds(@RequestParam(name = "ids") String ids) {
        List<SalesmanCustomerCountDo> result = salesmanCustomerRelation.selectCustomerCountBySalesmanIds(ids);
        if (result == null) {
            return new ArrayList<SalesmanCustomerCountDo>();
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
    public List<CustomerInfoDo> getBySalesmanId(@RequestParam("id") int id) {
        UserLevelDo userLevelDo = userLevel.selectByUserId(id);

        if (userLevelDo.getLevelId().equals(UserLevelEnum.ROLE_SALESMANAGER.getLeveId())){
            return customerInfo.getByManagerId(id);
        }else {
            return customerInfo.getBySalesmanId(id);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody CustomerInfoWithSalesmanResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            CustomerInfoDo customerInfoDo = customerInfo.selectByPrimaryKey(info.getCustomerId());
            customerInfoDo.setId(info.getCustomerId());
            customerInfoDo.setCustomerName(info.getCustomerName());
            customerInfoDo.setPhoneNumber(info.getPhoneNumber());
            customerInfoDo.setEmail(info.getEmail());
            customerInfoDo.setStatus(info.getStatus());
            customerInfo.updateByPrimaryKeySelective(customerInfoDo);

            SalesmanCustomerRelationDo salesmanCustomerRelationDo = salesmanCustomerRelation.selectByCustomerId(info.getCustomerId());
            salesmanCustomerRelationDo.setSalesmanId(info.getSalesmanId());
            salesmanCustomerRelation.updateByPrimaryKeySelective(salesmanCustomerRelationDo);
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
            CustomerInfoDo customerInfoDo = new CustomerInfoDo();
            customerInfoDo.setCustomerName(info.getCustomerName());
            customerInfoDo.setPhoneNumber(info.getPhoneNumber());
            customerInfoDo.setEmail(info.getEmail());
            customerInfoDo.setStatus(info.getStatus());
            customerInfoDo.setCreateTime(new Date());
            customerInfoDo.setCreator(UserSecurityUtils.getCurrentUserId());
            customerInfo.insert(customerInfoDo);

            SalesmanCustomerRelationDo salesmanCustomerRelationDo = new SalesmanCustomerRelationDo();
            salesmanCustomerRelationDo.setSalesmanId(info.getSalesmanId());
            salesmanCustomerRelationDo.setCustomerId(customerInfoDo.getId());
            salesmanCustomerRelation.insert(salesmanCustomerRelationDo);

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
            CustomerInfoDo customerInfoDo = customerInfo.selectByPrimaryKey(id);
            customerInfoDo.setStatus(false);

            customerInfo.updateByPrimaryKeySelective(customerInfoDo);

        } catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }
}
