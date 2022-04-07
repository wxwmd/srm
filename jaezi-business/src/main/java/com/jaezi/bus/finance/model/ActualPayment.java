package com.jaezi.bus.finance.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 16:56
 * @description
 * 实际付款情况
 */
public class ActualPayment extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     * 公司
     */
    private String company;

    /**
     * 会计年度
     */
    private String accountingYear;

    /**
     * 月份
     */
    private String month;

    /**
     * 付款金额
     */
    private Integer paymentAmount;

    /**
     * 文本
     */
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAccountingYear() {
        return accountingYear;
    }

    public void setAccountingYear(String accountingYear) {
        this.accountingYear = accountingYear;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
