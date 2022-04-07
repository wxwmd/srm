package com.jaezi.bus.financialAffairs.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/17  16:04:30
 * @description 标准物资发票
 */
public class StandardInvoice extends BaseModel {

    private Integer id;

    /**
     * 工厂
     */
    private Integer plant;

    /**
     * 顺序
     */
    private String order;

    /**
     * 物料凭证
     */
    private String materialVoucher;

    /**
     * 凭证项目
     */
    private String voucherProject;

    /**
     * 物料
     */
    private String material;

    /**
     * 物料描述
     */
    private String materialDescribe;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 行项目
     */
    private String hongProject;

    /**
     * 临时发票号
     */
    private Integer interimInvoiceNumber;

    /**
     * 总金额（不含税）
     */
    private BigDecimal aggregateAmount;

    /**
     * 采购订单
     */
    private String purchaseOrder;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 开出来的发票数量
     */
    private BigDecimal notOutInvoiceNumber;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 发票状态 0暂存 1已提交 2已维护 3已挂账
     */
    private Integer invoiceStatus;

    /**
     * 发票号
     */
    private String invoiceNumber;

    /**
     * 发票日期
     */
    private String invoiceDate;

    /**
     * 序号
     */
    private Integer serialNumber;

    /**
     * 不含税金额
     */
    private BigDecimal amount;

    /**
     * 税价
     */
    private BigDecimal taxPrice;

    /**
     * 当前状态
     */
    private String status;

    /**
     * 挂账日期
     */
    private String onAccountDate;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 开票日期
     */
    private String outInvoiceDate;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 税价合计
     */
    private BigDecimal taxPriceTotal;

    /**
     * 折扣原因
     */
    private String discountCause;

    /**
     * 审核状态 0未审核 1审核不通过，请废弃此发票 2审核通过
     */
    private Integer auditStatus;

    /**
     * 发票类型 0 标准物资发票  1返利红字发票
     */
    private Integer invoiceType;

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMaterialVoucher() {
        return materialVoucher;
    }

    public void setMaterialVoucher(String materialVoucher) {
        this.materialVoucher = materialVoucher;
    }

    public String getVoucherProject() {
        return voucherProject;
    }

    public void setVoucherProject(String voucherProject) {
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getHongProject() {
        return hongProject;
    }

    public void setHongProject(String hongProject) {
        this.hongProject = hongProject;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getInterimInvoiceNumber() {
        return interimInvoiceNumber;
    }

    public void setInterimInvoiceNumber(Integer interimInvoiceNumber) {
        this.interimInvoiceNumber = interimInvoiceNumber;
    }

    public BigDecimal getAggregateAmount() {
        return aggregateAmount;
    }

    public void setAggregateAmount(BigDecimal aggregateAmount) {
        this.aggregateAmount = aggregateAmount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getNotOutInvoiceNumber() {
        return notOutInvoiceNumber;
    }

    public void setNotOutInvoiceNumber(BigDecimal notOutInvoiceNumber) {
        this.notOutInvoiceNumber = notOutInvoiceNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
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

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnAccountDate() {
        return onAccountDate;
    }

    public void setOnAccountDate(String onAccountDate) {
        this.onAccountDate = onAccountDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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

    public BigDecimal getTaxPriceTotal() {
        return taxPriceTotal;
    }

    public void setTaxPriceTotal(BigDecimal taxPriceTotal) {
        this.taxPriceTotal = taxPriceTotal;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
