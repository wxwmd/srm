package com.jaezi.bus.purchase.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/23 9:07
 * @description 装车记录实体
 */
public class LoadingRecord extends BaseModel {

    private Integer id;

    /**
     * 装车单id
     */
    private Integer loadingDocumentId;

    /**
     * 采购订单号
     */
    private String purchaseOrder;

    /**
     * 行项目
     */
    private String lineItem;

    /**
     * 类别  （标准，寄售）
     */
    private String type;

    /**
     * 物料号
     */
    private String materialNumber;

    /**
     * 工厂
     */
    private String factory;

    /**
     * 物料描述
     */
    private String materialDescription;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 交货日期
     */
    private String deliveryDate;

    /**
     * 装车发运日期
     */
    private String loadingShipmentDate;

    /**
     * 订单数量
     */
    private Integer orderNumber;

    /**
     * 收货数量
     */
    private Integer receivingNumber;


    /**
     * 装车数量
     */
    private Integer loadingShipmentNumber;

    /**
     * 库存匹配
     */
    private Integer inventoryMatching;

    /**
     * 剩余数量
     */
    private Integer remainingQuantity;

    /**
     * 剩余承诺到货日期
     */
    private String remainingDate;

    /**
     * 单位
     */
    private String unit;

    /**
     * 库位
     */
    private String storageLocation;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 仓位
     */
    private String shippingSpace;

    /**
     * 版本号
     */
    private String versionNumber;

    /**
     * 大小量纲
     */
    private String dimensionSize;


    /**
     * 采购组
     */
    private String purchaseGroup;

    /**
     * 供应商物料号
     */
    private String supplierMaterial;

    /**
     * 采购员
     */
    private String purchasePerson;


    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 联系方式
     */
    private String contactInformation;

    /**
     * 入厂时间
     */
    private String factoryTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 二级界面文件地址
     */
    private String documentUrl;

    /**
     * 二级界面文件名称
     */
    private String documentName;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFactoryTime() {
        return factoryTime;
    }

    public void setFactoryTime(String factoryTime) {
        this.factoryTime = factoryTime;
    }

    public Integer getLoadingDocumentId() {
        return loadingDocumentId;
    }

    public void setLoadingDocumentId(Integer loadingDocumentId) {
        this.loadingDocumentId = loadingDocumentId;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineItem() {
        return lineItem;
    }

    public void setLineItem(String lineItem) {
        this.lineItem = lineItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
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

    public String getLoadingShipmentDate() {
        return loadingShipmentDate;
    }

    public void setLoadingShipmentDate(String loadingShipmentDate) {
        this.loadingShipmentDate = loadingShipmentDate;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getReceivingNumber() {
        return receivingNumber;
    }

    public void setReceivingNumber(Integer receivingNumber) {
        this.receivingNumber = receivingNumber;
    }

    public Integer getLoadingShipmentNumber() {
        return loadingShipmentNumber;
    }

    public void setLoadingShipmentNumber(Integer loadingShipmentNumber) {
        this.loadingShipmentNumber = loadingShipmentNumber;
    }

    public Integer getInventoryMatching() {
        return inventoryMatching;
    }

    public void setInventoryMatching(Integer inventoryMatching) {
        this.inventoryMatching = inventoryMatching;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getRemainingDate() {
        return remainingDate;
    }

    public void setRemainingDate(String remainingDate) {
        this.remainingDate = remainingDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getShippingSpace() {
        return shippingSpace;
    }

    public void setShippingSpace(String shippingSpace) {
        this.shippingSpace = shippingSpace;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDimensionSize() {
        return dimensionSize;
    }

    public void setDimensionSize(String dimensionSize) {
        this.dimensionSize = dimensionSize;
    }

    public String getPurchaseGroup() {
        return purchaseGroup;
    }

    public void setPurchaseGroup(String purchaseGroup) {
        this.purchaseGroup = purchaseGroup;
    }

    public String getSupplierMaterial() {
        return supplierMaterial;
    }

    public void setSupplierMaterial(String supplierMaterial) {
        this.supplierMaterial = supplierMaterial;
    }

    public String getPurchasePerson() {
        return purchasePerson;
    }

    public void setPurchasePerson(String purchasePerson) {
        this.purchasePerson = purchasePerson;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
