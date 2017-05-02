/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrmweb.controller;

import com.simon.fincrmweb.service.util.LogFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger LOGGER = LoggerFactory.getLogger(LogFileUtil.CONTROLLER);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        LOGGER.info("visit login Controller");
        return "login";
    }
}
