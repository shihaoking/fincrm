/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrmweb.controller;

import com.simon.fincrmweb.service.UserSecurityUtils;
import com.simon.fincrmweb.service.facade.ICustomerInfo;
import com.simon.fincrmweb.service.facade.ICustomerTraceLog;
import com.simon.fincrmprod.service.facade.enums.UserLevelEnum;
import com.simon.fincrmprod.service.facade.model.CustomerInfoModel;
import com.simon.fincrmprod.service.facade.model.CustomerTraceLogModel;
import com.simon.fincrmprod.service.facade.model.PageInfo;
import com.simon.fincrmprod.service.facade.request.CommonInfoQueryRequest;
import com.simon.fincrmprod.service.facade.result.CommonResult;
import com.simon.fincrmprod.service.facade.result.CustomerInfoQueryResult;
import com.simon.fincrmprod.service.facade.result.CustomerTraceLogQueryResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

        CustomerTraceLogQueryResult result;

        CommonInfoQueryRequest request = new CommonInfoQueryRequest();
        request.setPageSize(20);
        request.setPageNum(pageNum);

        if(id == -1){
            request.setId(UserSecurityUtils.getCurrentUserId());

            if(UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_SALESMANAGER.name())){
                result = customerReportLog.selectByManagerId(request);
            }else {
                result = customerReportLog.selectBySalesmanId(request);
            }
        }else {
            request.setId(id);
            result = customerReportLog.selectByCustomerId(request);
        }


        PageInfo pageInfo = result.getPageInfo();

        modelMap.addAttribute("pageInfo", pageInfo);

        modelMap.addAttribute("traceLogList", result.getCustomerTraceLogModelList());

        CustomerInfoModel customerInfoDo = customerInfo.selectByPrimaryKey(id);
        modelMap.addAttribute("customerInfo", customerInfoDo);

        CommonInfoQueryRequest customerListRequest = new CommonInfoQueryRequest();
        customerListRequest.setId(UserSecurityUtils.getCurrentUserId());

        CustomerInfoQueryResult customerInfoQueryResult;

        if(UserSecurityUtils.hasAnyRole(UserLevelEnum.ROLE_SALESMANAGER.name())){
            customerInfoQueryResult =  customerInfo.getByManagerId(customerListRequest);
        }else{
            customerInfoQueryResult =  customerInfo.getBySalesmanId(customerListRequest);
        }

        modelMap.addAttribute("customerList", customerInfoQueryResult.getCustomerInfoModelList());

        modelMap.addAttribute("requestCustomerId", id);
        return "/customerTraceLog/list";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public CustomerTraceLogModel get(@RequestParam("id") int id){
        return  customerReportLog.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody CustomerTraceLogModel record){
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
    public CommonResult update(@RequestBody CustomerTraceLogModel record){
        CommonResult commonResult = new CommonResult();

        CustomerTraceLogModel customerTraceLogDo = customerReportLog.selectByPrimaryKey(record.getId());
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
            CustomerTraceLogModel record = customerReportLog.selectByPrimaryKey(id);
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
