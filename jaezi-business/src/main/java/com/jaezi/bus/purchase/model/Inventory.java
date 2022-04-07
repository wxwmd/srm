package com.jaezi.bus.purchase.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 库存实体类
 */

public class Inventory extends BaseModel {

    /**
     * 库存ID
     */
    private Integer id;
    /**
     * 状态
     */
    private String status;
    /**
     * 采购组
     */
    private String purchase;
    /**
     * 工厂
     */
    private String plant;
    /**
     * 采购订单
     */
    private String pOrder;
    /**
     * 项目
     */
    private String project;
    /**
     * 物料
     */
    private String materialNumber;
    /**
     * 物料名称
     */
    private String materialName;
    /**
     * 创建日期
     */
    private String createDate;
    /**
     * 交货日期
     */
    private String deliveryDate;
    /**
     * 再计划日期
     */
    private String rescheduledDate;
    /**
     * 供应商入库日期
     */
    private String supplierEntryDate;
    /**
     * 订单数量
     */
    private BigDecimal orderQty;
    /**
     * 已交量
     */
    private BigDecimal deliveredQty;
    /**
     * 欠交量
     */
    private BigDecimal defQty;
    /**
     * 订单确认
     */
    private String orderConfirmation;
    /**
     * 库存匹配
     */
    private BigDecimal inventoryMatching;
    /**
     * 已装车数量
     */
    private BigDecimal installedQty;
    /**
     * 类型
     */
    private String type;
    /**
     * 单位
     */
    private String unit;
    /**
     * 供应商编码
     */
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getpOrder() {
        return pOrder;
    }

    public void setpOrder(String pOrder) {
        this.pOrder = pOrder;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(String rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
    }

    public String getSupplierEntryDate() {
        return supplierEntryDate;
    }

    public void setSupplierEntryDate(String supplierEntryDate) {
        this.supplierEntryDate = supplierEntryDate;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getDeliveredQty() {
        return deliveredQty;
    }

    public void setDeliveredQty(BigDecimal deliveredQty) {
        this.deliveredQty = deliveredQty;
    }

    public BigDecimal getDefQty() {
        return defQty;
    }

    public void setDefQty(BigDecimal defQty) {
        this.defQty = defQty;
    }

    public String getOrderConfirmation() {
        return orderConfirmation;
    }

    public void setOrderConfirmation(String orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public BigDecimal getInventoryMatching() {
        return inventoryMatching;
    }

    public void setInventoryMatching(BigDecimal inventoryMatching) {
        this.inventoryMatching = inventoryMatching;
    }

    public BigDecimal getInstalledQty() {
        return installedQty;
    }

    public void setInstalledQty(BigDecimal installedQty) {
        this.installedQty = installedQty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
