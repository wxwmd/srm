package com.jaezi.bus.purchase.vo;

import com.jaezi.bus.purchase.model.LoadingDocument;
import com.jaezi.bus.purchase.model.LoadingRecord;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 17:37
 * @description 装货单的vo对象
 */
public class LoadingDocumentVo extends LoadingDocument {

    private String remark;

    private String purchasePhone;

    private String supplierName;

    private String purchasePerson;

    private List<LoadingRecord> records;

    private String supplierHaulCycle;

    public String getSupplierHaulCycle() {
        return supplierHaulCycle;
    }

    public void setSupplierHaulCycle(String supplierHaulCycle) {
        this.supplierHaulCycle = supplierHaulCycle;
    }

    public List<LoadingRecord> getRecords() {
        return records;
    }

    public void setRecords(List<LoadingRecord> records) {
        this.records = records;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPurchasePhone() {
        return purchasePhone;
    }

    public void setPurchasePhone(String purchasePhone) {
        this.purchasePhone = purchasePhone;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPurchasePerson() {
        return purchasePerson;
    }

    public void setPurchasePerson(String purchasePerson) {
        this.purchasePerson = purchasePerson;
    }
}
