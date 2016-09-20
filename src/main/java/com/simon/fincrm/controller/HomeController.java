package com.simon.fincrm.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
public class HomeController {

    Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/")
    public String index(ModelMap map){

        return "index";
    }
}
