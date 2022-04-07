package com.jaezi.bus.purchase.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 采购实体类
 */

public class Purchase extends BaseModel {
    /**
     * 采购订单ID
     */
    @ExcelIgnore
    private Integer id;
    /**
     * 类型
     */
    @ExcelProperty("类型")
    private String type;
    /**
     * 状态
     */
    @ExcelIgnore
    private String status;
    /**
     * 状态(转换值)
     */
    @ExcelProperty("状态")
    private String statusFormat;
    /**
     * 未确认说明
     */
    @ExcelIgnore
    private String unDescription;
    /**
     * 未确认说明输入
     */
    @ExcelIgnore
    private String unDescriptionInput;
    /**
     * 紧急
     */
    @ExcelProperty("紧急")
    private Integer urgent;
    /**
     * 工厂
     */
    @ExcelProperty("工厂")
    private String plant;
    /**
     * 发布原因
     */
    @ExcelProperty("发布原因")
    private String releaseReason;
    /**
     * 采购订单
     */
    @ExcelProperty("采购订单")
    private String pOrder;
    /**
     * 项目
     */
    @ExcelProperty("项目")
    private String project;
    /**
     * 物料号
     */
    @ExcelProperty("物料号")
    private String materialNumber;
    /**
     * 物料名称
     */
    @ExcelProperty("物料名称")
    private String materialName;
    /**
     * 组件标识
     */
    @ExcelProperty("组件标识")
    private String componentIdentity;
    /**
     * 版本
     */
    @ExcelProperty("版本号")
    private String version;
    /**
     * 标识信息
     */
    @ExcelProperty("标识信息")
    private String identityInformation;
    /**
     * 数量
     */
    @ExcelProperty("数量")
    private BigDecimal qty;
    /**
     * 单位
     */
    @ExcelProperty("单位")
    private String unit;
    /**
     * 交货日期
     */
    @ExcelProperty("交货日期")
    private String deliveryDate;
    /**
     * 建立日期
     */
    @ExcelProperty("建立日期")
    private String createDate;
    /**
     * 订单原因
     */
    @ExcelProperty("订单原因")
    private String orderReason;
    /**
     * 单价
     */
    @ExcelIgnore
    private BigDecimal price;
    /**
     * 供应商编码
     */
    @ExcelProperty("供应商编码")
    private String supplierCode;
    /**
     * 物料描述
     */
    @ExcelIgnore
    private String materialDescription;
    /**
     * 再计划日期
     */
    @ExcelIgnore
    private String rescheduledDate;
    /**
     * 订单数量
     */
    @ExcelIgnore
    private BigDecimal orderQty;
    /**
     * 已交量
     */
    @ExcelIgnore
    private BigDecimal deliveredQty;
    /**
     * 欠交量
     */
    @ExcelIgnore
    private BigDecimal defQty;
    /**
     * 是否满足双周需求（Y/N)
     */
    @ExcelIgnore
    private String isSatisfy;
    /**
     * 数量1
     */
    @ExcelIgnore
    private BigDecimal qty1;
    /**
     * 交货期1
     */
    @ExcelIgnore
    private String deliveryDate1;
    /**
     * 数量2
     */
    @ExcelIgnore
    private BigDecimal qty2;
    /**
     * 交货期2
     */
    @ExcelIgnore
    private String deliveryDate2;
    /**
     * 数量3
     */
    @ExcelIgnore
    private BigDecimal qty3;
    /**
     * 交货期3
     */
    @ExcelIgnore
    private String deliveryDate3;
    /**
     * 备注
     */
    @ExcelIgnore
    private String remark;
    /**
     * 装车发运日期
     */
    @ExcelIgnore
    private String loadingShipmentDate;
    /**
     * 收货数量
     */
    @ExcelIgnore
    private Integer receivingNumber;
    /**
     * 已装车数量
     */
    @ExcelIgnore
    private Integer alLoadingShipmentNumber;
    /**
     * 库存匹配
     */
    @ExcelIgnore
    private Integer inventoryMatching;
    /**
     * 库位
     */
    @ExcelIgnore
    private String storageLocation;
    /**
     * 大小量纲
     */
    @ExcelIgnore
    private String dimensionSize;
    /**
     * 采购组
     */
    @ExcelIgnore
    private String purchaseGroup;
    /**
     * 供应商物料号
     */
    @ExcelIgnore
    private String supplierMaterial;
    /**
     * 采购员
     */
    @ExcelIgnore
    private String purchasePerson;
    /**
     * 联系方式
     */
    @ExcelIgnore
    private String contactInformation;
    /**
     * 公司地址
     */
    @ExcelIgnore
    private String companyAddress;
    /**
     * 调整类型
     */
    @ExcelIgnore
    private Integer adjustType;
    /**
     * 质检状态
     */
    @ExcelProperty("质检状态")
    private String qmStatus;
    /**
     * 质检不合格数
     */
    @ExcelProperty("质检不合格数")
    private BigDecimal unqualifiedQty;
    /**
     * 采购凭证
     */
    @ExcelProperty("订单凭证")
    private String purchaseCredentials;
    /**
     * 审核状态
     */
    @ExcelIgnore
    private String auditStatus;

    /**
     * 阅读日期
     */
    @ExcelProperty("阅读日期")
    private String readDate;

    /**
     * 交货确认
     */
    @ExcelProperty("交货确认")
    private String deliveryConfirm;

    /**
     * 装运信息
     */
    @ExcelProperty("装运信息")
    private String shipmentInfo;

    /**
     * 收货状态
     */
    @ExcelProperty("收货状态")
    private String receiveStatus;

    /**
     * 收货数量
     */
    @ExcelProperty("收货数量")
    private Integer receiveAmount;

    /**
     * 质检状态
     */
    @ExcelIgnore
    private String qualityTestingStatus;

    /**
     * 未质检通过数
     */
    @ExcelIgnore
    private Integer qtnop;

    /**
     * 订单说明
     */
    @ExcelProperty("订单说明")
    private String orderExplanation;

    public String getStatusFormat() {
        return statusFormat;
    }

    public void setStatusFormat(String statusFormat) {
        this.statusFormat = statusFormat;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getPurchaseCredentials() {
        return purchaseCredentials;
    }

    public void setPurchaseCredentials(String purchaseCredentials) {
        this.purchaseCredentials = purchaseCredentials;
    }

    public String getQmStatus() {
        return qmStatus;
    }

    public void setQmStatus(String qmStatus) {
        this.qmStatus = qmStatus;
    }

    public BigDecimal getUnqualifiedQty() {
        return unqualifiedQty;
    }

    public void setUnqualifiedQty(BigDecimal unqualifiedQty) {
        this.unqualifiedQty = unqualifiedQty;
    }

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Integer adjustType) {
        this.adjustType = adjustType;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(String rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
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

    public String getIsSatisfy() {
        return isSatisfy;
    }

    public void setIsSatisfy(String isSatisfy) {
        this.isSatisfy = isSatisfy;
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }

    public String getDeliveryDate1() {
        return deliveryDate1;
    }

    public void setDeliveryDate1(String deliveryDate1) {
        this.deliveryDate1 = deliveryDate1;
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public void setQty2(BigDecimal qty2) {
        this.qty2 = qty2;
    }

    public String getDeliveryDate2() {
        return deliveryDate2;
    }

    public void setDeliveryDate2(String deliveryDate2) {
        this.deliveryDate2 = deliveryDate2;
    }

    public BigDecimal getQty3() {
        return qty3;
    }

    public void setQty3(BigDecimal qty3) {
        this.qty3 = qty3;
    }

    public String getDeliveryDate3() {
        return deliveryDate3;
    }

    public void setDeliveryDate3(String deliveryDate3) {
        this.deliveryDate3 = deliveryDate3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLoadingShipmentDate() {
        return loadingShipmentDate;
    }

    public void setLoadingShipmentDate(String loadingShipmentDate) {
        this.loadingShipmentDate = loadingShipmentDate;
    }

    public Integer getReceivingNumber() {
        return receivingNumber;
    }

    public void setReceivingNumber(Integer receivingNumber) {
        this.receivingNumber = receivingNumber;
    }

    public Integer getAlLoadingShipmentNumber() {
        return alLoadingShipmentNumber;
    }

    public void setAlLoadingShipmentNumber(Integer alLoadingShipmentNumber) {
        this.alLoadingShipmentNumber = alLoadingShipmentNumber;
    }

    public Integer getInventoryMatching() {
        return inventoryMatching;
    }

    public void setInventoryMatching(Integer inventoryMatching) {
        this.inventoryMatching = inventoryMatching;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
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

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnDescription() {
        return unDescription;
    }

    public void setUnDescription(String unDescription) {
        this.unDescription = unDescription;
    }

    public String getUnDescriptionInput() {
        return unDescriptionInput;
    }

    public void setUnDescriptionInput(String unDescriptionInput) {
        this.unDescriptionInput = unDescriptionInput;
    }

    public Integer getUrgent() {
        return urgent;
    }

    public void setUrgent(Integer urgent) {
        this.urgent = urgent;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getReleaseReason() {
        return releaseReason;
    }

    public void setReleaseReason(String releaseReason) {
        this.releaseReason = releaseReason;
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

    public String getComponentIdentity() {
        return componentIdentity;
    }

    public void setComponentIdentity(String componentIdentity) {
        this.componentIdentity = componentIdentity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdentityInformation() {
        return identityInformation;
    }

    public void setIdentityInformation(String identityInformation) {
        this.identityInformation = identityInformation;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getDeliveryConfirm() {
        return deliveryConfirm;
    }

    public void setDeliveryConfirm(String deliveryConfirm) {
        this.deliveryConfirm = deliveryConfirm;
    }

    public String getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Integer getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(Integer receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public String getQualityTestingStatus() {
        return qualityTestingStatus;
    }

    public void setQualityTestingStatus(String qualityTestingStatus) {
        this.qualityTestingStatus = qualityTestingStatus;
    }

    public Integer getQtnop() {
        return qtnop;
    }

    public void setQtnop(Integer qtnop) {
        this.qtnop = qtnop;
    }

    public String getOrderExplanation() {
        return orderExplanation;
    }

    public void setOrderExplanation(String orderExplanation) {
        this.orderExplanation = orderExplanation;
    }
}
