/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinshihao
 * @version $Id: CommonResult.java, v 0.1 2016-09-10 15:47 jinshihao Exp $$
 */
public class CommonResult {
    private boolean success = true;
    private String errorMsg;
    private Map<String, String> externalData = new HashMap();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>errorMsg</tt>.
     *
     * @return property value of errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter method for property <tt>errorMsg</tt>.
     *
     * @param errorMsg value to be assigned to property errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Getter method for property <tt>externalData</tt>.
     *
     * @return property value of externalData
     */
    public Map<String, String> getExternalData() {
        return externalData;
    }

    /**
     * Setter method for property <tt>externalData</tt>.
     *
     * @param externalData value to be assigned to property externalData
     */
    public void setExternalData(Map<String, String> externalData) {
        this.externalData = externalData;
    }
}
