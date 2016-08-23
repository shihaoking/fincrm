package com.simon.fincrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController {
    @RequestMapping("/list")
    public String getList(ModelMap modelMap){
        return "salesman/list";
    }
}
