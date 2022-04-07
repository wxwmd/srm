package com.jaezi.bus.inventory.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 外协分包库存实体类
 */
public class OutsourcingSubcontractInventory extends BaseModel {

    /**
     * id
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
    private String  materialDescription;
    /**
     * 工厂
     */
    @ExcelProperty(value = "工厂")
    private Integer plant;
    /**
     * 库存数量（非限制）
     */
    @ExcelProperty(value = "库存数量（非限制）")
    private BigDecimal qty;
    /**
     * 字通库存
     */
    @ExcelProperty(value = "字通库存")
    private BigDecimal qty1;

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

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public Integer getPlant() {
        return plant;
    }

    public void setPlant(Integer plant) {
        this.plant = plant;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }
}
