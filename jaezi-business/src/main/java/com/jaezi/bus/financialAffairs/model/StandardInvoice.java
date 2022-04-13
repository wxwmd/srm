package com.jaezi.bus.financialAffairs.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author wxw
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/04/13
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
     * 供应商名称
     */
    private String supplierName;

    /**
     * 行项目
     */
    private String hongProject;

    /**
     * 临时发票号
     */
    private Integer interimInvoiceNumber;



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
     * 发票状态
     * 0 已暂存
     * 1 已提交
     * 2 已挂账
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
     * 不含税金额
     */
    private BigDecimal withoutTaxAmount;

    /**
     * 税价
     */
    private BigDecimal taxAmount;


    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 税价合计
     */
    private BigDecimal totalAmount;

    /**
     * 折扣原因
     */
    private String discountCause;

//    /**
//     * 审核状态 0未审核 1审核不通过，请废弃此发票 2审核通过
//     */
//    private Integer auditStatus;




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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public BigDecimal getWithoutTaxAmount() {
        return withoutTaxAmount;
    }

    public void setWithoutTaxAmount(BigDecimal withoutTaxAmount) {
        this.withoutTaxAmount = withoutTaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    @Override
    public String toString() {
        return "StandardInvoice{" +
                "id=" + id +
                ", plant=" + plant +
                ", order='" + order + '\'' +
                ", materialVoucher='" + materialVoucher + '\'' +
                ", voucherProject='" + voucherProject + '\'' +
                ", material='" + material + '\'' +
                ", materialDescribe='" + materialDescribe + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", hongProject='" + hongProject + '\'' +
                ", interimInvoiceNumber=" + interimInvoiceNumber +
                ", withoutTaxAmount=" + withoutTaxAmount +
                ", purchaseOrder='" + purchaseOrder + '\'' +
                ", unitPrice=" + unitPrice +
                ", notOutInvoiceNumber=" + notOutInvoiceNumber +
                ", createTime='" + createTime + '\'' +
                ", invoiceStatus=" + invoiceStatus +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", serialNumber=" + serialNumber +
                ", onAccountDate='" + onAccountDate + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", outInvoiceDate='" + outInvoiceDate + '\'' +
                ", amount=" + withoutTaxAmount +
                ", taxAmount=" + taxAmount +
                ", taxRate=" + taxRate +
                ", totalAmount=" + totalAmount +
                ", discountCause='" + discountCause + '\'' +
                ", invoiceType=" + invoiceType +
                '}';
    }
}
