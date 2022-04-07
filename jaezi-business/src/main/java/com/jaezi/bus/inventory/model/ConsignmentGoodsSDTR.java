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
 * @description 寄售物资结收发存实体类
 */

public class ConsignmentGoodsSDTR extends BaseModel {
    /**
     * 寄售物资结收发存ID
     */
    @ExcelIgnore
    private Integer id;
    /**
     * 工厂
     */
    @ExcelProperty(value = "工厂")
    private String factory;
    /**
     * 物料名称
     */
    @ExcelProperty(value = "物料名称")
    private String materialName;

    /**
     * 物料名称
     */
    @ExcelProperty(value = "物料号")
    private String materialNumber;

    /**
     * 期初
     */
    @ExcelProperty(value = "期初")
    private BigDecimal firstTerm;
    /**
     * 收入
     */
    @ExcelProperty(value = "收入")
    private BigDecimal income;
    /**
     * 发出
     */
    @ExcelProperty(value = "发出")
    private BigDecimal emit;
    /**
     * 期末
     */
    @ExcelProperty(value = "期末")
    private BigDecimal lastTerm;
    /**
     * 不含税金额
     */
    @ExcelProperty(value = "不含税金额")
    private BigDecimal afterTax;
    /**
     * 未结算
     */
    @ExcelProperty(value = "未结算")
    private BigDecimal outstanding;
    /**
     * 已结算
     */
    @ExcelProperty(value = "已结算")
    private BigDecimal clsd;
    /**
     * 行号
     */
    @ExcelProperty(value = "行号")
    private Integer line;
    /**
     * 供应商编码
     */
    @ExcelProperty(value = "供应商编码")
    private String supplierCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public BigDecimal getFirstTerm() {
        return firstTerm;
    }

    public void setFirstTerm(BigDecimal firstTerm) {
        this.firstTerm = firstTerm;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getEmit() {
        return emit;
    }

    public void setEmit(BigDecimal emit) {
        this.emit = emit;
    }

    public BigDecimal getLastTerm() {
        return lastTerm;
    }

    public void setLastTerm(BigDecimal lastTerm) {
        this.lastTerm = lastTerm;
    }

    public BigDecimal getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(BigDecimal afterTax) {
        this.afterTax = afterTax;
    }

    public BigDecimal getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(BigDecimal outstanding) {
        this.outstanding = outstanding;
    }

    public BigDecimal getClsd() {
        return clsd;
    }

    public void setClsd(BigDecimal clsd) {
        this.clsd = clsd;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }


}
