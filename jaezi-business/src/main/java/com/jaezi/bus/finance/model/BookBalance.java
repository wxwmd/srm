package com.jaezi.bus.finance.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 16:58
 * @description
 * 账面余额
 */
public class BookBalance extends BaseModel {

    /**
     * id
     */
    @ExcelIgnore
    private Integer id;

    /**
     *公司
     */
    @ExcelProperty(value = "公司")
    private String company;

    /**
     * 日期
     */
    @ExcelProperty(value = "日期")
    private String date;

    /**
     * 账面余额
     */
    @ExcelProperty(value = "账面余额")
    private Integer bookBalance;

    /**
     * 科目
     */
    @ExcelProperty(value = "科目")
    private String subject;

    /**
     * 借贷方
     */
    @ExcelProperty(value = "借贷方")
    private String lenders;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBookBalance() {
        return bookBalance;
    }

    public void setBookBalance(Integer bookBalance) {
        this.bookBalance = bookBalance;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLenders() {
        return lenders;
    }

    public void setLenders(String lenders) {
        this.lenders = lenders;
    }
}
