package com.simon.fincrm.controller;

import com.simon.fincrm.dal.dao.CustomerInfoDao;
import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.simon.fincrm.dal.model.SalesmanCustomerCountDo;
import com.simon.fincrm.service.facade.ICustomerInfo;
import com.simon.fincrm.service.facade.ISalesmanCustomerRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id", required = false, defaultValue = "-1") int id){
        if (id != -1){
            List<CustomerInfoDo>  result = customerInfo.getByCustomerId(id);
            modelMap.addAttribute("customerList", result);
            modelMap.addAttribute("customerCount", result.size());
        }
        return "customer/list";
    }

    @RequestMapping(value = "getCountBySalesmanIds", method = RequestMethod.GET)
    @ResponseBody
    public List<SalesmanCustomerCountDo> getListByIds(@RequestParam(name = "ids") String ids){
        List<SalesmanCustomerCountDo> result = salesmanCustomerRelation.selectCustomerCountBySalesmanIds(ids);
        if(result == null){
            return new ArrayList<SalesmanCustomerCountDo>();
        }

        return result;
    }
}
