package com.jaezi.bus.purchase.vo;

import com.jaezi.bus.purchase.model.LoadingRecord;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/23 14:31
 * @description
 * 装车单记录Vo类
 */
public class LoadingRecordVo extends LoadingRecord {

    private String purchaseId;

    private String supplierCode;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
