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
 * @description 直送入库信息实体类
 */

public class DirectInputInformation extends BaseModel {


    @ExcelIgnore
    private Integer id;

    /**
     * 直达编号
     */
    @ExcelProperty(value = "直达编号")
    private Integer nonstopNumber;
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
     *入库数量
     */
    @ExcelProperty(value = "入库数量")
    private BigDecimal qty;
    /**
     *日期
     */
    @ExcelProperty(value = "日期")
    private String date;

    /**
     * 供应商编码
     */
    @ExcelProperty(value = "供应商编码")
    private String supplierCode;

    /**
     * 物料描述
     */
    @ExcelProperty(value = "物料描述")
    private String materialDescribe;

    /**
     * 采购订单
     */
    @ExcelProperty(value = "采购订单")
    private String purchaseOrder;

    /**
     * 行项目
     */
    @ExcelProperty(value = "行项目")
    private String hongProject;

    /**
     * 生产订单
     */
    @ExcelProperty(value = "生产订单")
    private String productionOrder;

    /**
     * 直送类型
     */
    @ExcelProperty(value = "直送类型")
    private String directSendingType;

    /**
     * 装车单号
     */
    @ExcelProperty(value = "装车单号")
    private String loadingListNumber;

    public Integer getNonstopNumber() {
        return nonstopNumber;
    }

    public void setNonstopNumber(Integer nonstopNumber) {
        this.nonstopNumber = nonstopNumber;
    }

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

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getHongProject() {
        return hongProject;
    }

    public void setHongProject(String hongProject) {
        this.hongProject = hongProject;
    }

    public String getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(String productionOrder) {
        this.productionOrder = productionOrder;
    }

    public String getDirectSendingType() {
        return directSendingType;
    }

    public void setDirectSendingType(String directSendingType) {
        this.directSendingType = directSendingType;
    }

    public String getLoadingListNumber() {
        return loadingListNumber;
    }

    public void setLoadingListNumber(String loadingListNumber) {
        this.loadingListNumber = loadingListNumber;
    }
}
