package com.jaezi.bus.financialAffairs.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author wxw
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/4/24
 * @description 寄售物资开票
 */
public class ConsignmentSalesInvoiceOut extends BaseModel {

    @ExcelIgnore
    private Integer id;

    /**
     * 工厂
     */
    @ExcelProperty("工厂")
    private Integer plant;

    /**
     * 物料号
     */
    @ExcelProperty("物料号")
    private String materialNumber;

    /**
     * 物料名称
     */
    @ExcelProperty("物料名称")
    private String materialName;

    /**
     * 数量
     */
    @ExcelProperty("数量")
    private BigDecimal quantity;

    /**
     * 开票区间
     */
    @ExcelProperty("开票区间")
    private String outInvoicePeriod;

    /**
     * 不含税金额
     */
    @ExcelProperty("不含税金额")
    private BigDecimal amount;

    /**
     * 税价合计
     */
    @ExcelProperty("税价合计")
    private BigDecimal taxPriceTotal;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private String createTime;

    /**
     * 供应商编码
     */
    @ExcelIgnore
    private String supplierCode;

    /*
    * 供应商名称
    * */
    private String supplierName;

    /**
     * 采购订单
     */
    @ExcelProperty("采购订单")
    private String purchaseOrder;

    /**
     * 物料
     */
    @ExcelProperty("物料")
    private String material;

    /**
     * 物料描述
     */
    @ExcelProperty("物料描述")
    private String materialDescribe;

    /**
     * 未开票数量
     */
    @ExcelProperty("未开票数量")
    private Integer notOutInvoiceNumber;

    /**
     * 单价
     */
    @ExcelIgnore
    private BigDecimal unitPrice;

    /**
     * 状态 -1未开票 0已提交 1已挂账
     */
    @ExcelProperty("状态 -1未开票 0已提交 1已挂账")
    private Integer status;

    /**
     * 税率
     */
    @ExcelProperty("税率")
    private BigDecimal taxRate;

    /**
     * 发票组
     */
    @ExcelProperty("发票组")
    private String invoiceGroup;

    /**
     * 税率
     */
    @ExcelProperty("时间区间")
    private String temporalInterval;



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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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

    public Integer getNotOutInvoiceNumber() {
        return notOutInvoiceNumber;
    }

    public void setNotOutInvoiceNumber(Integer notOutInvoiceNumber) {
        this.notOutInvoiceNumber = notOutInvoiceNumber;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getInvoiceGroup() {
        return invoiceGroup;
    }

    public void setInvoiceGroup(String invoiceGroup) {
        this.invoiceGroup = invoiceGroup;
    }

    public String getTemporalInterval() {
        return temporalInterval;
    }

    public void setTemporalInterval(String temporalInterval) {
        this.temporalInterval = temporalInterval;
    }

    @Override
    public String toString() {
        return "ConsignmentSalesInvoiceOut{" +
                "id=" + id +
                ", plant=" + plant +
                ", materialNumber='" + materialNumber + '\'' +
                ", materialName='" + materialName + '\'' +
                ", quantity=" + quantity +
                ", outInvoicePeriod='" + outInvoicePeriod + '\'' +
                ", amount=" + amount +
                ", taxPriceTotal=" + taxPriceTotal +
                ", createTime='" + createTime + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", purchaseOrder='" + purchaseOrder + '\'' +
                ", material='" + material + '\'' +
                ", materialDescribe='" + materialDescribe + '\'' +
                ", notOutInvoiceNumber=" + notOutInvoiceNumber +
                ", unitPrice=" + unitPrice +
                ", status=" + status +
                ", taxRate=" + taxRate +
                ", invoiceGroup='" + invoiceGroup + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", temporalInterval='" + temporalInterval + '\'' +
                '}';
    }

}
