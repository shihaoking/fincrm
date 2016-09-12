package com.simon.fincrm.controller;

import com.simon.fincrm.dal.model.CustomerInfoDo;
import com.simon.fincrm.dal.model.SalesmanCustomerCountDo;
import com.simon.fincrm.dal.model.SalesmanCustomerRelationDo;
import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.service.facade.ICustomerInfo;
import com.simon.fincrm.service.facade.ISalesmanCustomerRelation;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.result.CommonResult;
import com.simon.fincrm.service.result.CustomerInfoWithSalesmanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id", required = false, defaultValue = "-1") int id) {
        List<CustomerInfoDo> result = new ArrayList<CustomerInfoDo>();

        if (id != -1) {
            result = customerInfo.getBySalesmanId(id);

        }else{
            result = customerInfo.selectAll(true);
        }

        modelMap.addAttribute("customerList", result);
        modelMap.addAttribute("customerCount", result.size());

        List<UserInfoDo> salesmanList = userInfo.selectAll(true);
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

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody CustomerInfoWithSalesmanResult info) {
        CommonResult commonResult = new CommonResult();

        try {
            CustomerInfoDo customerInfoDo = new CustomerInfoDo();
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
            CustomerInfoDo customerInfoDo = customerInfo.selectByPrimaryKey(info.getCustomerId());
            customerInfoDo.setId(info.getCustomerId());
            customerInfoDo.setCustomerName(info.getCustomerName());
            customerInfoDo.setPhoneNumber(info.getPhoneNumber());
            customerInfoDo.setEmail(info.getEmail());
            customerInfoDo.setStatus(info.getStatus());
            customerInfoDo.setCreateTime(new Date());
            customerInfoDo.setCreator(1);
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
    public CommonResult delete(@RequestParam("id") int id){
        CommonResult commonResult = new CommonResult();

        try{
            CustomerInfoDo customerInfoDo = customerInfo.selectByPrimaryKey(id);
            customerInfoDo.setStatus(false);

            customerInfo.updateByPrimaryKeySelective(customerInfoDo);

        }catch (Exception ex){
            commonResult.setSuccess(false);
            commonResult.setErrorMsg(ex.getMessage());
            return  commonResult;
        }

        commonResult.setSuccess(true);
        return  commonResult;
    }
}
