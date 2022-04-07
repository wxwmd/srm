package com.jaezi.bus.financialAffairs.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/31  9:23:51
 * @description
 */
public class ConsignmentSalesInvoiceOutInfo extends BaseModel {

    private Integer id;

    /**
     * 工厂
     */
    private Integer plant;

    /**
     * 物料号
     */
    private String materialNumber;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 开票区间
     */
    private String outInvoicePeriod;

    /**
     * 物料描述
     */
    private String materialDescribe;

    /**
     * 采购订单
     */
    private String purchaseOrder;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 发票号
     */
    private Integer invoiceNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getOutInvoicePeriod() {
        return outInvoicePeriod;
    }

    public void setOutInvoicePeriod(String outInvoicePeriod) {
        this.outInvoicePeriod = outInvoicePeriod;
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getPlant() {
        return plant;
    }

    public void setPlant(Integer plant) {
        this.plant = plant;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
