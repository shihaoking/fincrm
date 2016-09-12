/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.controller;

import com.simon.fincrm.dal.model.CustomerTraceLogDo;
import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.service.facade.ICustomerInfo;
import com.simon.fincrm.service.facade.ICustomerTraceLog;
import com.simon.fincrm.service.facade.IUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private IUserInfo userInfo;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id") int id, @RequestParam(name = "salesmanId", required = false, defaultValue = "-1") int salesmanId) {

        List<CustomerTraceLogDo> result = customerReportLog.selectByCustomerId(id);

        modelMap.addAttribute("traceLogList", result);
        modelMap.addAttribute("traceLogCount", result.size());

        List<UserInfoDo> salesmanList = userInfo.selectAll(true);
        modelMap.addAttribute("salesmanList", salesmanList);
        modelMap.addAttribute("filterSalesmanId", salesmanId);

        return "/customerTraceLog/list";
    }

}
