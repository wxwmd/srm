package com.jaezi.bus.financialAffairs.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9  17:25:15
 * @description
 * 标准物资开票
 */
public class StandardInvoiceOut extends BaseModel {


    @ExcelProperty("序号")
    private Integer id;

    /**
     * 工厂
     */
    @ExcelProperty("工厂")
    private Integer plant;

    /**
     * 顺序
     */
    @ExcelProperty("顺序")
    private String order;

    /**
     * 物料凭证
     */
    @ExcelProperty("物料凭证")
    private String materialVoucher;

    /**
     * 凭证项目
     */
    @ExcelProperty("凭证项目")
    private String voucherProject;

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
     * 供应商编码
     */
    @ExcelIgnore
    private String supplierCode;

    /**
     * 行项目
     */
    @ExcelProperty("行项目")
    private String hongProject;

    /**
     * 临时发票号
     */
    @ExcelProperty("临时发票号")
    private Integer interimInvoiceNumber;

    /**
     * 总金额（不含税）
     */
    @ExcelProperty("总金额（不含税）")
    private BigDecimal aggregateAmount;

    /**
     * 采购订单
     */
    @ExcelProperty("采购订单")
    private String purchaseOrder;

    /**
     * 未开票数量
     */
    @ExcelProperty("未开票数量")
    private BigDecimal notOutInvoiceNumber;

    /**
     * 单价
     */
    @ExcelIgnore
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    @ExcelProperty("数量")
    private BigDecimal quantity;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private String createTime;

    /**
     * 状态 -1未开票 0已开票
     */
    @ExcelIgnore
    private Integer status;

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

    public BigDecimal getNotOutInvoiceNumber() {
        return notOutInvoiceNumber;
    }

    public void setNotOutInvoiceNumber(BigDecimal notOutInvoiceNumber) {
        this.notOutInvoiceNumber = notOutInvoiceNumber;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
