package com.jaezi.srminterface.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 物料/供应商中间表
 */

public class MaterialSupplier extends BaseModel {
    /**
     * 物料号
     */
    private String materialNumber;

    /**
     * 供应商编号
     */
    private String supplierCode;

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
