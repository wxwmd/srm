package com.jaezi.bus.purchase.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 生产计划导入实体类
 */

public class ProductionPlanImport extends BaseModel {
    /**
     * 库存导入ID
     */
    @ExcelIgnore
    private Integer id;
    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号")
    private String materialNumber;
    /**
     * 物料描述
     */
    @ExcelProperty(value = "物料描述")
    private String materialDescription;
    /**
     * 供应商库存
     */
    @ExcelProperty(value = "供应商库存")
    private BigDecimal supplierInventory;
    /**
     * 订货数量
     */
    @ExcelProperty(value = "订货数量")
    private BigDecimal orderQty;
    /**
     * 库存差异
     */
    @ExcelProperty(value = "库存差异")
    private BigDecimal inventoryDiff;
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private String updateTime;
    /**
     * 供应商编码
     */
    @ExcelProperty(value = "供应商编码")
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public BigDecimal getSupplierInventory() {
        return supplierInventory;
    }

    public void setSupplierInventory(BigDecimal supplierInventory) {
        this.supplierInventory = supplierInventory;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getInventoryDiff() {
        return inventoryDiff;
    }

    public void setInventoryDiff(BigDecimal inventoryDiff) {
        this.inventoryDiff = inventoryDiff;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
