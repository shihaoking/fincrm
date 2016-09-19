/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.result;

import java.util.Date;

/**
 * @author jinshihao
 * @version $Id: SalesmanInfoWithManagerResult.java, v 0.1 2016-09-13 16:08 jinshihao Exp $$
 */
public class SalesmanInfoWithManagerResult {
    private Integer salesmanId;

    private String userName;

    private String userPwd;

    private String email;

    private Date birthday;

    private Boolean gender;

    private String address;

    private Integer managerId;

    private Date updateTime;

    private Date createTime;

    private String phonenumber;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    /**
     * Getter method for property <tt>managerId</tt>.
     *
     * @return property value of managerId
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * Setter method for property <tt>managerId</tt>.
     *
     * @param managerId value to be assigned to property managerId
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
