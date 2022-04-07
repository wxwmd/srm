package com.jaezi.bus.finance.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 16:17
 * @description
 * 返利红字发票
 */
public class RateRedInvoice extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 发票号
     */
    private String invoiceNumber;

    /**
     * 发票日期
     */
    private String invoiceDate;

    /**
     * 不含税金额
     */
    private BigDecimal taxExclusivePrice;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 税额
     */
    private BigDecimal taxAmount;

    /**
     * 税价合计
     */
    private BigDecimal taxPriceTotal;

    /**
     * 折扣原因
     */
    private String discountReason;

    /**
     * 红字发票信息表编码
     */
    private String redInvoiceInformationCode;


    /**
     *SPA_code
     */
    private String sPACode;


    /**
     *公司
     */
    private String company;

    /**
     *会计年度
     */
    private String accountingYear;

    /**
     *月份
     */
    private String month;

    /**
     *付款日期
     */
    private String paymentDate;

    /**
     *金额
     */
    private Integer money;

    /**
     *付款方式
     */
    private String accountingMode;

    /**
     *账户
     */
    private String account;

    /**
     *银行名称
     */
    private String bankName;

    private String supplierCode;

    /**
     * 是否折扣
     */
    private Integer isDiscount;

    /**
     * 工厂
     */
    private Integer plant;

    public Integer getPlant() {
        return plant;
    }

    public void setPlant(Integer plant) {
        this.plant = plant;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public String getSupplierCode() {
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getsPACode() {
        return sPACode;
    }

    public void setsPACode(String sPACode) {
        this.sPACode = sPACode;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getAccountingMode() {
        return accountingMode;
    }

    public void setAccountingMode(String accountingMode) {
        this.accountingMode = accountingMode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getTaxExclusivePrice() {
        return taxExclusivePrice;
    }

    public void setTaxExclusivePrice(BigDecimal taxExclusivePrice) {
        this.taxExclusivePrice = taxExclusivePrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxPriceTotal() {
        return taxPriceTotal;
    }

    public void setTaxPriceTotal(BigDecimal taxPriceTotal) {
        this.taxPriceTotal = taxPriceTotal;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public String getRedInvoiceInformationCode() {
        return redInvoiceInformationCode;
    }

    public void setRedInvoiceInformationCode(String redInvoiceInformationCode) {
        this.redInvoiceInformationCode = redInvoiceInformationCode;
    }
}
