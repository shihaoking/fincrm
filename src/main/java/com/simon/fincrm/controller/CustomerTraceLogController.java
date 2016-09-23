/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.controller;

import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.simon.fincrm.dal.model.CustomerTraceLogDo;
import com.simon.fincrm.service.UserSecurityUtils;
import com.simon.fincrm.service.enums.UserLevelEnum;
import com.simon.fincrm.service.facade.ICustomerInfo;
import com.simon.fincrm.service.facade.ICustomerTraceLog;
import com.simon.fincrm.service.interceptor.PageInterceptor;
import com.simon.fincrm.service.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author jinshihao
 * @version $Id: CustomerTraceLogController.java, v 0.1 2016-09-12 17:42 jinshihao Exp $$
 */
@Controller
@RequestMapping("customerTraceLog")
public class CustomerTraceLogController {
    @Autowired
    private ICustomerInfo customerInfo;

    @Autowired
    private ICustomerTraceLog customerReportLog;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id", defaultValue = "-1") int id, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) {

        PageInterceptor.startPage(pageNum, 20);

        List<CustomerTraceLogDo> result;
        if(id == -1){
            if(UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_MANAGER.name())){
                result = customerReportLog.selectByManagerId(UserSecurityUtils.getCurrentUserId());
            }else {
                result = customerReportLog.selectBySalesmanId(UserSecurityUtils.getCurrentUserId());
            }
        }else {
            result = customerReportLog.selectByCustomerId(id);
        }


        PageInterceptor.Page page = PageInterceptor.endPage();
        modelMap.addAttribute("pageInfo", page);

        modelMap.addAttribute("traceLogList", result);
        modelMap.addAttribute("traceLogCount", result.size());

        CustomerInfoDo customerInfoDo = customerInfo.selectByPrimaryKey(id);
        modelMap.addAttribute("customerInfo", customerInfoDo);

        if(UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_MANAGER.name())){
            List<CustomerInfoDo> customerInfoDoList =  customerInfo.getByManagerId(UserSecurityUtils.getCurrentUserId());
            modelMap.addAttribute("customerList", customerInfoDoList);
        }else{
            List<CustomerInfoDo> customerInfoDoList =  customerInfo.getBySalesmanId(UserSecurityUtils.getCurrentUserId());
            modelMap.addAttribute("customerList", customerInfoDoList);
        }


        modelMap.addAttribute("requestCustomerId", id);
        return "/customerTraceLog/list";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public CustomerTraceLogDo get(@RequestParam("id") int id){
        return  customerReportLog.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody CustomerTraceLogDo record){
        CommonResult commonResult = new CommonResult();

        record.setCreator(UserSecurityUtils.getCurrentUser().getUserId());
        record.setCreateTime(new Date());

        try {
            record.setCreateTime(new Date());
            customerReportLog.insert(record);
        }catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody CustomerTraceLogDo record){
        CommonResult commonResult = new CommonResult();

        CustomerTraceLogDo customerTraceLogDo = customerReportLog.selectByPrimaryKey(record.getId());
        try {
            customerTraceLogDo.setReportInfo(record.getReportInfo());
            customerTraceLogDo.setStatus(record.getStatus());
            customerTraceLogDo.setCustomerId(record.getCustomerId());
            customerTraceLogDo.setReportSalesmanId(record.getReportSalesmanId());

            customerReportLog.updateByPrimaryKeySelective(record);
        }catch (Exception ex) {
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return commonResult;
        }

        commonResult.setSuccess(true);
        return commonResult;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("id") int id){
        CommonResult commonResult = new CommonResult();

        try{
            CustomerTraceLogDo record = customerReportLog.selectByPrimaryKey(id);
            record.setStatus(false);

            customerReportLog.updateByPrimaryKeySelective(record);

        }catch (Exception ex){
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return  commonResult;
        }

        commonResult.setSuccess(true);
        return  commonResult;
    }
}
