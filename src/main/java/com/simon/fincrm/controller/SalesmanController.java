package com.simon.fincrm.controller;

import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.service.facade.IUserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by jinshihao on 16/8/23.
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController {

    Logger logger = Logger.getLogger("controller");

    @Autowired
    private IUserInfo userInfo;

    @RequestMapping("/list")
    public String getList(ModelMap modelMap){
        List<UserInfoDo> result = userInfo.selectAll(true);
        modelMap.addAttribute("salesmanList", result);
        return "salesman/list";
    }
}
