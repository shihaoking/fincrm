package com.simon.fincrm.dal.model;

/**
 * Created by jinshihao on 16/8/25.
 */
public class SalesmanCustomerCountDo {
    private int salesmanId;
    private int customerCount;

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setSalemanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }
}
