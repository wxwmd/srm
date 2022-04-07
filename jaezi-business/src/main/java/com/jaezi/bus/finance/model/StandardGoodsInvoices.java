package com.jaezi.bus.finance.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 标准物资发票实体类
 */

public class StandardGoodsInvoices extends BaseModel {
    /**
     * 序号
     */
    private Integer id;
    /**
     * 临时发票号
     */
    private Integer provisionalInvoice;
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
    private String afterTax;
    /**
     * 税价合计
     */
    private BigDecimal totalTaxPrice;
    /**
     * 当前状态
     */
    private String status;
    /**
     * 挂账日期
     */
    private String paymentDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvisionalInvoice() {
        return provisionalInvoice;
    }

    public void setProvisionalInvoice(Integer provisionalInvoice) {
        this.provisionalInvoice = provisionalInvoice;
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

    public String getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(String afterTax) {
        this.afterTax = afterTax;
    }

    public BigDecimal getTotalTaxPrice() {
        return totalTaxPrice;
    }

    public void setTotalTaxPrice(BigDecimal totalTaxPrice) {
        this.totalTaxPrice = totalTaxPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
