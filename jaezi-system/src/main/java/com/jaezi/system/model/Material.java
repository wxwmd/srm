package com.jaezi.system.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/5/19 19:13
 * @description 物料实体类
 */

public class Material extends BaseModel {
//    /**
//     * 物料ID
//     */
//    @ExcelIgnore
//    private String id;
    /**
     * 物料号
     */
    @ExcelProperty("物料编码")
    private String materialNumber;
    /**
     * 物料名称
     */
    @ExcelProperty("物料名称")
    private String materialName;
    /**
     * 物料描述
     */
    @ExcelProperty("物料描述")
    private String materialDescription;
    /**
     * 采购员编码
     */
    @ExcelProperty("采购员编码")
    private String buyerNumber;
    /**
     * 单位
     */
    @ExcelProperty("单位")
    private String unit;
    /**
     * 规格
     */
    @ExcelProperty("规格")
    private String specification;
    /**
     * 流量稳定性分类
     */
    @ExcelProperty("流量稳定性分类")
    private String trafficStabilityClassification;
    /**
     * 流量分类
     */
    @ExcelIgnore
    private String trafficClassification;
    /**
     * 风险分类
     */
    @ExcelProperty("风险分类")
    private String riskClassification;
    /**
     * 工厂
     */
    @ExcelIgnore
    private String plant;
    /**
     * 舍入值(最小包装)
     */
    @ExcelProperty("舍入值")
    private String minimalPackage;
    /**
     * 最小批次
     */
    @ExcelProperty("最小批次")
    private String minimumQuantity;
    /**
     * 送货频次
     */
    @ExcelProperty("送货频次")
    private String deliveryFrequency;
    /**
     * 采购周期
     */
    @ExcelProperty("采购周期")
    private String purchaseDay;
    /**
     * 采购类型
     */
    @ExcelProperty("采购类型")
    private String procurementType;
    /**
     * 品类
     */
    @ExcelProperty("种类")
    private String category;
    /**
     * 社会通用性分类
     */
    @ExcelIgnore
    private String socialGeneralityClassification;
    /**
     * 供货方式
     */
    @ExcelProperty("供货方式")
    private String supplyMode;
    /**
     * 采购组
     */
    @ExcelProperty("采购组")
    private String purchaseGroup;
    /**
     * 单价
     */
    @ExcelIgnore
    private String price;
//    /**
//     * 供应商
//     */
//    @ExcelIgnore
//    private String supplierCode;

    @ExcelIgnore
    private String utf1;

    @ExcelIgnore
    private String utf2;

    @ExcelIgnore
    private String utf3;

    @ExcelIgnore
    private String utf4;

    @ExcelIgnore
    private String utf5;

    @ExcelIgnore
    private String utf6;

    @ExcelIgnore
    private String utf7;

    @ExcelIgnore
    private String utf8;

    @ExcelIgnore
    private String utf9;

    @ExcelIgnore
    private String utf10;

//    @ExcelIgnore
//    private String utf11;
//
//    @ExcelIgnore
//    private String utf12;
//
//    @ExcelIgnore
//    private String utf13;
//
//    @ExcelIgnore
//    private String utf14;
//
//    @ExcelIgnore
//    private String utf15;

//    public String getSupplierCode() {
//        return supplierCode;
//    }
//
//    public void setSupplierCode(String supplierCode) {
//        this.supplierCode = supplierCode;
//    }

//    public String getUtf15() {
//        return utf15;
//    }
//
//    public void setUtf15(String utf15) {
//        this.utf15 = utf15;
//    }
//
//    public String getUtf11() {
//        return utf11;
//    }
//
//    public void setUtf11(String utf11) {
//        this.utf11 = utf11;
//    }
//
//    public String getUtf12() {
//        return utf12;
//    }
//
//    public void setUtf12(String utf12) {
//        this.utf12 = utf12;
//    }
//
//    public String getUtf13() {
//        return utf13;
//    }
//
//    public void setUtf13(String utf13) {
//        this.utf13 = utf13;
//    }
//
//    public String getUtf14() {
//        return utf14;
//    }
//
//    public void setUtf14(String utf14) {
//        this.utf14 = utf14;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getMinimalPackage() {
        return minimalPackage;
    }

    public void setMinimalPackage(String minimalPackage) {
        this.minimalPackage = minimalPackage;
    }

    public String getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(String minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public String getDeliveryFrequency() {
        return deliveryFrequency;
    }

    public void setDeliveryFrequency(String deliveryFrequency) {
        this.deliveryFrequency = deliveryFrequency;
    }

    public String getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(String purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public String getProcurementType() {
        return procurementType;
    }

    public void setProcurementType(String procurementType) {
        this.procurementType = procurementType;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUtf1() {
        return utf1;
    }

    public void setUtf1(String utf1) {
        this.utf1 = utf1;
    }

    public String getUtf2() {
        return utf2;
    }

    public void setUtf2(String utf2) {
        this.utf2 = utf2;
    }

    public String getUtf3() {
        return utf3;
    }

    public void setUtf3(String utf3) {
        this.utf3 = utf3;
    }

    public String getUtf4() {
        return utf4;
    }

    public void setUtf4(String utf4) {
        this.utf4 = utf4;
    }

    public String getUtf5() {
        return utf5;
    }

    public void setUtf5(String utf5) {
        this.utf5 = utf5;
    }

    public String getUtf6() {
        return utf6;
    }

    public void setUtf6(String utf6) {
        this.utf6 = utf6;
    }

    public String getUtf7() {
        return utf7;
    }

    public void setUtf7(String utf7) {
        this.utf7 = utf7;
    }

    public String getUtf8() {
        return utf8;
    }

    public void setUtf8(String utf8) {
        this.utf8 = utf8;
    }

    public String getUtf9() {
        return utf9;
    }

    public void setUtf9(String utf9) {
        this.utf9 = utf9;
    }

    public String getUtf10() {
        return utf10;
    }

    public void setUtf10(String utf10) {
        this.utf10 = utf10;
    }
}
