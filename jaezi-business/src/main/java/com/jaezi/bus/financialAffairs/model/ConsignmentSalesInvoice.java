package com.jaezi.bus.financialAffairs.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author wxw
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/4/26
 * @description 寄售物资发票
 */
public class ConsignmentSalesInvoice extends BaseModel {

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
     * 开票期间
     */
    private String outInvoicePeriod;

    /**
     * 不含税金额
     */
    private BigDecimal amount;

    /**
     * 税额
     */
    private BigDecimal taxAmount;

    /**
     * 税价合计
     */
    private BigDecimal taxPriceTotal;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
    * 供应商名称
    */
    private String supplierName;

    /**
     * 发票代码
     */
    private Integer invoiceCode;

    /**
     * 发票号
     */
    private Integer invoiceNumber;

    /**
     * 开票日期
     */
    private String outInvoiceDate;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 采购订单
     */
    private String purchaseOrder;

    /**
     * 行项目
     */
    private Integer hongProject;

    /**
     * 物料凭证
     */
    private Integer materialVoucher;

    /**
     * 凭证项目
     */
    private Integer voucherProject;

    /**
     * 物料
     */
    private String material;

    /**
     * 物料描述
     */
    private String materialDescribe;

    /**
     * 发票日期
     */
    private String invoiceDate;

    /**
     * 发票状态 0已提交 1已挂账
     */
    private Integer invoiceStatus;

    /**
     * 临时发票号
     */
    private Integer interimInvoiceNumber;

    /**
     * 折扣原因
     */
    private String discountCause;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlant() {
        return plant;
    }

    public void setPlant(Integer plant) {
        this.plant = plant;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(Integer invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOutInvoiceDate() {
        return outInvoiceDate;
    }

    public void setOutInvoiceDate(String outInvoiceDate) {
        this.outInvoiceDate = outInvoiceDate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getHongProject() {
        return hongProject;
    }

    public void setHongProject(Integer hongProject) {
        this.hongProject = hongProject;
    }

    public Integer getMaterialVoucher() {
        return materialVoucher;
    }

    public void setMaterialVoucher(Integer materialVoucher) {
        this.materialVoucher = materialVoucher;
    }

    public Integer getVoucherProject() {
        return voucherProject;
    }

    public void setVoucherProject(Integer voucherProject) {
        this.voucherProject = voucherProject;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getInterimInvoiceNumber() {
        return interimInvoiceNumber;
    }

    public void setInterimInvoiceNumber(Integer interimInvoiceNumber) {
        this.interimInvoiceNumber = interimInvoiceNumber;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }
}
