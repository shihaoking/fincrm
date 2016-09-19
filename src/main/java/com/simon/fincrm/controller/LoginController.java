/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.controller;

import com.simon.fincrm.service.facade.IUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jinshihao
 * @version $Id: UserController.java, v 0.1 2016-09-18 14:53 jinshihao Exp $$
 */
@Controller
@RequestMapping
public class LoginController {
    @Autowired
    private IUserInfo userInfo;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
