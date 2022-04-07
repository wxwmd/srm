package com.jaezi.bus.purchase.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 11:49
 * @description
 * 供应商基础数据
 */
public class SupplierBasicData extends BaseModel {
    /**
     * id
     */
    private Integer id;

    /**
     * 物料号
     */
    private String materialNumber;

    /**
     * 物料描述
     */
    private String materialDescription;

    /**
     * 流量稳定性分类
     */
    private String trafficStabilityClassification;

    /**
     * 流量分类
     */
    private String trafficClassification;

    /**
     * 风险分类
     */
    private String riskClassification;

    /**
     * 工厂
     */
    private Integer factory;

    /**
     * 系统现有值（舍入值  最小包装）
     */
    private Integer roundSysExistingValue;

    /**
     * 供应商反馈设置数量（舍入值 最小包装）
     */
    private Integer roundSupplierFeedbackSetQuantity;

    /**
     * 企业审核意见（舍入值 最小包装）
     */
    private String roundEnterpriseAuditOpinions;
    /**
     * 企业审核意见（最小批量 供应商反馈设置数量）
     */
    private String roundBatchEnterpriseAuditOpinions;
    /**
     * 企业审核意见（最小批量 供应商设置必要性）
     */
    private String roundBatchNecessityEnterpriseAuditOpinions;
    /**
     * 企业审核意见（送货频次 供应商维护建议）
     */
    private String adviseEnterpriseAuditOpinions;
    /**
     * 企业审核意见（原材料准备周期）
     */
    private String rawMaterialPeriodEnterpriseAuditOpinions;
    /**
     * 企业审核意见（计划锁定周期）
     */
    private String lockPlanEnterpriseAuditOpinions;
    /**
     * 企业审核意见（生产周期）
     */
    private String productionEnterpriseAuditOpinions;
    /**
     * 企业审核意见（运输周期）
     */
    private String transportationEnterpriseAuditOpinions;
    /**
     * 企业审核意见（舍入值 最小包装）
     */
    private String totalEnterpriseAuditOpinions;

    /**
     * 企业确认时间（舍入值  最小包装）
     */
    private String roundEnterpriseConfirmationDate;

    /**
     * 系统现有值（最小批量）
     */
    private Integer miniQuantitySysExistingValue;

    /**
     * 供应商反馈设置数量（最小批量）
     */
    private Integer miniQuantitySupplierFeedbackSetQuantity;

    /**
     * 供应商设置必要性（最小批量）
     */
    private String miniQuantitySupplierSetNecessity;

    /**
     * 企业审核意见（最小批量）
     */
    private String miniQuantityEnterpriseAuditOpinions;

    /**
     * 企业回馈结论（最小批量）
     */
    private String miniQuantityEnterpriseFeedbackConclusion;

    /**
     * 企业确认时间（最小批量）
     */
    private String miniQuantityEnterpriseConfirmationDate;


    /**
     * 送货频次
     */
    private String deliveryFrequency;

    /**
     * 关键零部件名称
     */
    private String keyComponentName;

    /**
     * 關鍵零部件庫存
     */
    private String keyComponentInventory;

    /**
     * 原材料/半成品准备周期(实际采购周期/天)
     */
    private Integer actualMaterialsReadyDay;

    /**
     * 计划锁定周期（实际采购周期/天）
     */
    private Integer actualPlanLockDay;

    /**
     * 生产周期（实际采购周期/天）
     */
    private Integer actualProductionDay;

    /**
     * 运输周期（实际采购周期/天）
     */
    private Integer actualTransportationDay;

    /**
     * 总计（实际采购周期/天）
     */
    private Integer actualTotal;

    /**
     * 采购周期
     */
    private Integer purchaseDay;

    /**
     * 采购类型
     */
    private Integer purchaseType;

    /**
     * 品类
     */
    private String category;

    /**
     * 社会通用性分类
     */
    private String socialGeneralityClassification;

    /**
     * 供货方式
     */
    private String supplyMode;

    /**
     * 采购组
     */
    private String purchaseGroup;

    /**
     * 更新日期
     */
    private String updateDate;

    /**
     * 供应商编码
     */
    private String supplierCode;


    public String getRoundBatchEnterpriseAuditOpinions() {
        return roundBatchEnterpriseAuditOpinions;
    }

    public void setRoundBatchEnterpriseAuditOpinions(String roundBatchEnterpriseAuditOpinions) {
        this.roundBatchEnterpriseAuditOpinions = roundBatchEnterpriseAuditOpinions;
    }

    public String getRoundBatchNecessityEnterpriseAuditOpinions() {
        return roundBatchNecessityEnterpriseAuditOpinions;
    }

    public void setRoundBatchNecessityEnterpriseAuditOpinions(String roundBatchNecessityEnterpriseAuditOpinions) {
        this.roundBatchNecessityEnterpriseAuditOpinions = roundBatchNecessityEnterpriseAuditOpinions;
    }

    public String getAdviseEnterpriseAuditOpinions() {
        return adviseEnterpriseAuditOpinions;
    }

    public void setAdviseEnterpriseAuditOpinions(String adviseEnterpriseAuditOpinions) {
        this.adviseEnterpriseAuditOpinions = adviseEnterpriseAuditOpinions;
    }

    public String getRawMaterialPeriodEnterpriseAuditOpinions() {
        return rawMaterialPeriodEnterpriseAuditOpinions;
    }

    public void setRawMaterialPeriodEnterpriseAuditOpinions(String rawMaterialPeriodEnterpriseAuditOpinions) {
        this.rawMaterialPeriodEnterpriseAuditOpinions = rawMaterialPeriodEnterpriseAuditOpinions;
    }

    public String getLockPlanEnterpriseAuditOpinions() {
        return lockPlanEnterpriseAuditOpinions;
    }

    public void setLockPlanEnterpriseAuditOpinions(String lockPlanEnterpriseAuditOpinions) {
        this.lockPlanEnterpriseAuditOpinions = lockPlanEnterpriseAuditOpinions;
    }

    public String getProductionEnterpriseAuditOpinions() {
        return productionEnterpriseAuditOpinions;
    }

    public void setProductionEnterpriseAuditOpinions(String productionEnterpriseAuditOpinions) {
        this.productionEnterpriseAuditOpinions = productionEnterpriseAuditOpinions;
    }

    public String getTransportationEnterpriseAuditOpinions() {
        return transportationEnterpriseAuditOpinions;
    }

    public void setTransportationEnterpriseAuditOpinions(String transportationEnterpriseAuditOpinions) {
        this.transportationEnterpriseAuditOpinions = transportationEnterpriseAuditOpinions;
    }

    public String getTotalEnterpriseAuditOpinions() {
        return totalEnterpriseAuditOpinions;
    }

    public void setTotalEnterpriseAuditOpinions(String totalEnterpriseAuditOpinions) {
        this.totalEnterpriseAuditOpinions = totalEnterpriseAuditOpinions;
    }

    public Integer getActualProductionDay() {
        return actualProductionDay;
    }

    public void setActualProductionDay(Integer actualProductionDay) {
        this.actualProductionDay = actualProductionDay;
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

    public String getTrafficStabilityClassification() {
        return trafficStabilityClassification;
    }

    public void setTrafficStabilityClassification(String trafficStabilityClassification) {
        this.trafficStabilityClassification = trafficStabilityClassification;
    }

    public String getTrafficClassification() {
        return trafficClassification;
    }

    public void setTrafficClassification(String trafficClassification) {
        this.trafficClassification = trafficClassification;
    }

    public String getRiskClassification() {
        return riskClassification;
    }

    public void setRiskClassification(String riskClassification) {
        this.riskClassification = riskClassification;
    }

    public Integer getFactory() {
        return factory;
    }

    public void setFactory(Integer factory) {
        this.factory = factory;
    }

    public Integer getRoundSysExistingValue() {
        return roundSysExistingValue;
    }

    public void setRoundSysExistingValue(Integer roundSysExistingValue) {
        this.roundSysExistingValue = roundSysExistingValue;
    }

    public Integer getRoundSupplierFeedbackSetQuantity() {
        return roundSupplierFeedbackSetQuantity;
    }

    public void setRoundSupplierFeedbackSetQuantity(Integer roundSupplierFeedbackSetQuantity) {
        this.roundSupplierFeedbackSetQuantity = roundSupplierFeedbackSetQuantity;
    }

    public String getRoundEnterpriseAuditOpinions() {
        return roundEnterpriseAuditOpinions;
    }

    public void setRoundEnterpriseAuditOpinions(String roundEnterpriseAuditOpinions) {
        this.roundEnterpriseAuditOpinions = roundEnterpriseAuditOpinions;
    }

    public String getRoundEnterpriseConfirmationDate() {
        return roundEnterpriseConfirmationDate;
    }

    public void setRoundEnterpriseConfirmationDate(String roundEnterpriseConfirmationDate) {
        this.roundEnterpriseConfirmationDate = roundEnterpriseConfirmationDate;
    }

    public Integer getMiniQuantitySysExistingValue() {
        return miniQuantitySysExistingValue;
    }

    public void setMiniQuantitySysExistingValue(Integer miniQuantitySysExistingValue) {
        this.miniQuantitySysExistingValue = miniQuantitySysExistingValue;
    }

    public Integer getMiniQuantitySupplierFeedbackSetQuantity() {
        return miniQuantitySupplierFeedbackSetQuantity;
    }

    public void setMiniQuantitySupplierFeedbackSetQuantity(Integer miniQuantitySupplierFeedbackSetQuantity) {
        this.miniQuantitySupplierFeedbackSetQuantity = miniQuantitySupplierFeedbackSetQuantity;
    }

    public String getMiniQuantitySupplierSetNecessity() {
        return miniQuantitySupplierSetNecessity;
    }

    public void setMiniQuantitySupplierSetNecessity(String miniQuantitySupplierSetNecessity) {
        this.miniQuantitySupplierSetNecessity = miniQuantitySupplierSetNecessity;
    }

    public String getMiniQuantityEnterpriseAuditOpinions() {
        return miniQuantityEnterpriseAuditOpinions;
    }

    public void setMiniQuantityEnterpriseAuditOpinions(String miniQuantityEnterpriseAuditOpinions) {
        this.miniQuantityEnterpriseAuditOpinions = miniQuantityEnterpriseAuditOpinions;
    }

    public String getMiniQuantityEnterpriseFeedbackConclusion() {
        return miniQuantityEnterpriseFeedbackConclusion;
    }

    public void setMiniQuantityEnterpriseFeedbackConclusion(String miniQuantityEnterpriseFeedbackConclusion) {
        this.miniQuantityEnterpriseFeedbackConclusion = miniQuantityEnterpriseFeedbackConclusion;
    }

    public String getMiniQuantityEnterpriseConfirmationDate() {
        return miniQuantityEnterpriseConfirmationDate;
    }

    public void setMiniQuantityEnterpriseConfirmationDate(String miniQuantityEnterpriseConfirmationDate) {
        this.miniQuantityEnterpriseConfirmationDate = miniQuantityEnterpriseConfirmationDate;
    }

    public String getDeliveryFrequency() {
        return deliveryFrequency;
    }

    public void setDeliveryFrequency(String deliveryFrequency) {
        this.deliveryFrequency = deliveryFrequency;
    }

    public String getKeyComponentName() {
        return keyComponentName;
    }

    public void setKeyComponentName(String keyComponentName) {
        this.keyComponentName = keyComponentName;
    }

    public String getKeyComponentInventory() {
        return keyComponentInventory;
    }

    public void setKeyComponentInventory(String keyComponentInventory) {
        this.keyComponentInventory = keyComponentInventory;
    }

    public Integer getActualMaterialsReadyDay() {
        return actualMaterialsReadyDay;
    }

    public void setActualMaterialsReadyDay(Integer actualMaterialsReadyDay) {
        this.actualMaterialsReadyDay = actualMaterialsReadyDay;
    }

    public Integer getActualPlanLockDay() {
        return actualPlanLockDay;
    }

    public void setActualPlanLockDay(Integer actualPlanLockDay) {
        this.actualPlanLockDay = actualPlanLockDay;
    }

    public Integer getActualTransportationDay() {
        return actualTransportationDay;
    }

    public void setActualTransportationDay(Integer actualTransportationDay) {
        this.actualTransportationDay = actualTransportationDay;
    }

    public Integer getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Integer actualTotal) {
        this.actualTotal = actualTotal;
    }

    public Integer getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(Integer purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSocialGeneralityClassification() {
        return socialGeneralityClassification;
    }

    public void setSocialGeneralityClassification(String socialGeneralityClassification) {
        this.socialGeneralityClassification = socialGeneralityClassification;
    }

    public String getSupplyMode() {
        return supplyMode;
    }

    public void setSupplyMode(String supplyMode) {
        this.supplyMode = supplyMode;
    }

    public String getPurchaseGroup() {
        return purchaseGroup;
    }

    public void setPurchaseGroup(String purchaseGroup) {
        this.purchaseGroup = purchaseGroup;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
