package com.jaezi.bus.finance.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 寄售物资发票实体类
 */

public class ConsignmentInvoice extends BaseModel {
    /**
     * 序号
     */
    private Integer id;
    /**
     * 开票期间(ERP)
     */
    private String provisionalInvoice;
    /**
     * 不含税金额(ERP)
     */
    private BigDecimal afterTaxErp;
    /**
     * 税额(ERP)
     */
    private BigDecimal taxErp;
    /**
     * 税价合计(ERP)
     */
    private BigDecimal totalTaxPriceErp;
    /**
     * 发票代码
     */
    private Integer invoiceCode;
    /**
     * 发票号
     */
    private Integer invoiceNo;
    /**
     * 发票日期
     */
    private String invoiceDate;
    /**
     * 不含税金额
     */
    private BigDecimal afterTax;
    /**
     * 税率
     */
    private BigDecimal taxRate;
    /**
     * 税额
     */
    private BigDecimal tax;
    /**
     * 税价合计
     */
    private BigDecimal totalTaxPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvisionalInvoice() {
        return provisionalInvoice;
    }

    public void setProvisionalInvoice(String provisionalInvoice) {
        this.provisionalInvoice = provisionalInvoice;
    }

    public BigDecimal getAfterTaxErp() {
        return afterTaxErp;
    }

    public void setAfterTaxErp(BigDecimal afterTaxErp) {
        this.afterTaxErp = afterTaxErp;
    }

    public BigDecimal getTaxErp() {
        return taxErp;
    }

    public void setTaxErp(BigDecimal taxErp) {
        this.taxErp = taxErp;
    }

    public BigDecimal getTotalTaxPriceErp() {
        return totalTaxPriceErp;
    }

    public void setTotalTaxPriceErp(BigDecimal totalTaxPriceErp) {
        this.totalTaxPriceErp = totalTaxPriceErp;
    }

    public Integer getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(Integer invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(BigDecimal afterTax) {
        this.afterTax = afterTax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalTaxPrice() {
        return totalTaxPrice;
    }

    public void setTotalTaxPrice(BigDecimal totalTaxPrice) {
        this.totalTaxPrice = totalTaxPrice;
    }
}
