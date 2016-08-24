package com.simon.fincrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getList(ModelMap modelMap, @RequestParam(name = "id", required = false, defaultValue = "-1") int id){
        modelMap.addAttribute("id", id);
        return "customer/list";
    }
}
