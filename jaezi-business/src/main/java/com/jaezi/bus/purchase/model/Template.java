package com.jaezi.bus.purchase.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/5 11:49
 * @description 导出模板实体类
 */

public class Template extends BaseModel {

    @ExcelProperty("物料号")
    private Integer materialNumber;
    @ExcelProperty("供应商编码")
    private String supplierCode;
    @ExcelProperty("供应商库存")
    private BigDecimal supplierQty;

    public Integer getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Integer materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public BigDecimal getSupplierQty() {
        return supplierQty;
    }

    public void setSupplierQty(BigDecimal supplierQty) {
        this.supplierQty = supplierQty;
    }
}
