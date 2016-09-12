package com.simon.fincrm.dal.model;

import java.util.Date;

public class CustomerTraceLogDo {
    private Integer id;

    private Integer customerId;

    private String reportInfo;

    private Integer creator;

    /**
     * Getter method for property <tt>creatorName</tt>.
     *
     * @return property value of creatorName
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Setter method for property <tt>creatorName</tt>.
     *
     * @param creatorName value to be assigned to property creatorName
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    private String creatorName;

    private Date createTime;

    /**
     * Getter method for property <tt>reportSalesman</tt>.
     *
     * @return property value of reportSalesman
     */
    public String getReportSalesman() {
        return reportSalesman;
    }

    /**
     * Setter method for property <tt>reportSalesman</tt>.
     *
     * @param reportSalesman value to be assigned to property reportSalesman
     */
    public void setReportSalesman(String reportSalesman) {
        this.reportSalesman = reportSalesman;
    }

    private String reportSalesman;

    /**
     * Getter method for property <tt>reportSalesmanId</tt>.
     *
     * @return property value of reportSalesmanId
     */
    public Integer getReportSalesmanId() {
        return reportSalesmanId;
    }

    /**
     * Setter method for property <tt>reportSalesmanId</tt>.
     *
     * @param reportSalesmanId value to be assigned to property reportSalesmanId
     */
    public void setReportSalesmanId(Integer reportSalesmanId) {
        this.reportSalesmanId = reportSalesmanId;
    }

    private Integer reportSalesmanId;

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo == null ? null : reportInfo.trim();
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
}