/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.result;

import com.simon.fincrm.dal.model.CustomerInfoDo;

import java.util.Date;

/**
 * @author jinshihao
 * @version $Id: CustomerInfoWithSalesmanResult.java, v 0.1 2016-09-10 14:43 jinshihao Exp $$
 */
public class CustomerInfoWithSalesmanResult {
    private int salesmanId;

    private Integer customerId;

    private String customerName;

    private String phoneNumber;

    private String email;

    private Boolean status;

    private Integer creator;

    private Date createTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer id) {
        this.customerId = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * Getter method for property <tt>salesmanId</tt>.
     *
     * @return property value of salesmanId
     */
    public int getSalesmanId() {
        return salesmanId;
    }

    /**
     * Setter method for property <tt>salesmanId</tt>.
     *
     * @param salesmanId value to be assigned to property salesmanId
     */
    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
