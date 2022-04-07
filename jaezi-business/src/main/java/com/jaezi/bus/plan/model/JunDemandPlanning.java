package com.jaezi.bus.plan.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 六月需求计划实体类
 */

public class JunDemandPlanning extends BaseModel {
    /**
     * 六月需求计划ID
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
     * 工厂
     */
    private String plant;
    /**
     * 物料类别
     */
    private String materialType;
    /**
     * 转换单位量
     */
    private Integer conversionUnit;
    /**
     * 单位
     */
    private String unit;
    /**
     * 供应商
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * MRP控制者
     */
    private String mrp;
    /**
     * 维护类型
     */
    private String maintainType;
    /**
     * 产品1需求
     */
    private Integer zeroMaterial1;
    /**
     * 产品2需求
     */
    private Integer zeroMaterial2;
    /**
     * 产品3需求
     */
    private Integer zeroMaterial3;
    /**
     * 总计需求
     */
    private Integer zeroSum;
    /**
     * 产品1需求
     */
    private Integer oneMaterial1;
    /**
     * 产品2需求
     */
    private Integer oneMaterial2;
    /**
     * 产品3需求
     */
    private Integer oneMaterial3;
    /**
     * 总计需求
     */
    private Integer oneSum;
    /**
     * 产品1需求
     */
    private Integer twoMaterial1;
    /**
     * 产品2需求
     */
    private Integer twoMaterial2;
    /**
     * 产品3需求
     */
    private Integer twoMaterial3;
    /**
     * 总计需求
     */
    private Integer twoSum;
    /**
     * 产品1需求
     */
    private Integer threeMaterial1;
    /**
     * 产品2需求
     */
    private Integer threeMaterial2;
    /**
     * 产品3需求
     */
    private Integer threeMaterial3;
    /**
     * 总计需求
     */
    private Integer threeSum;
    /**
     * 产品1需求
     */
    private Integer fourMaterial1;
    /**
     * 产品2需求
     */
    private Integer fourMaterial2;
    /**
     * 产品3需求
     */
    private Integer fourMaterial3;
    /**
     * 总计需求
     */
    private Integer fourSum;
    /**
     * 产品1需求
     */
    private Integer fiveMaterial1;
    /**
     * 产品2需求
     */
    private Integer fiveMaterial2;
    /**
     * 产品3需求
     */
    private Integer fiveMaterial3;
    /**
     * 总计需求
     */
    private Integer fiveSum;
    /**
     * 产品1需求
     */
    private Integer sixMaterial1;
    /**
     * 产品2需求
     */
    private Integer sixMaterial2;
    /**
     * 产品3需求
     */
    private Integer sixMaterial3;
    /**
     * 总计需求
     */
    private Integer sixSum;

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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public Integer getConversionUnit() {
        return conversionUnit;
    }

    public void setConversionUnit(Integer conversionUnit) {
        this.conversionUnit = conversionUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(String maintainType) {
        this.maintainType = maintainType;
    }

    public Integer getZeroMaterial1() {
        return zeroMaterial1;
    }

    public void setZeroMaterial1(Integer zeroMaterial1) {
        this.zeroMaterial1 = zeroMaterial1;
    }

    public Integer getZeroMaterial2() {
        return zeroMaterial2;
    }

    public void setZeroMaterial2(Integer zeroMaterial2) {
        this.zeroMaterial2 = zeroMaterial2;
    }

    public Integer getZeroMaterial3() {
        return zeroMaterial3;
    }

    public void setZeroMaterial3(Integer zeroMaterial3) {
        this.zeroMaterial3 = zeroMaterial3;
    }

    public Integer getZeroSum() {
        return zeroSum;
    }

    public void setZeroSum(Integer zeroSum) {
        this.zeroSum = zeroSum;
    }

    public Integer getOneMaterial1() {
        return oneMaterial1;
    }

    public void setOneMaterial1(Integer oneMaterial1) {
        this.oneMaterial1 = oneMaterial1;
    }

    public Integer getOneMaterial2() {
        return oneMaterial2;
    }

    public void setOneMaterial2(Integer oneMaterial2) {
        this.oneMaterial2 = oneMaterial2;
    }

    public Integer getOneMaterial3() {
        return oneMaterial3;
    }

    public void setOneMaterial3(Integer oneMaterial3) {
        this.oneMaterial3 = oneMaterial3;
    }

    public Integer getOneSum() {
        return oneSum;
    }

    public void setOneSum(Integer oneSum) {
        this.oneSum = oneSum;
    }

    public Integer getTwoMaterial1() {
        return twoMaterial1;
    }

    public void setTwoMaterial1(Integer twoMaterial1) {
        this.twoMaterial1 = twoMaterial1;
    }

    public Integer getTwoMaterial2() {
        return twoMaterial2;
    }

    public void setTwoMaterial2(Integer twoMaterial2) {
        this.twoMaterial2 = twoMaterial2;
    }

    public Integer getTwoMaterial3() {
        return twoMaterial3;
    }

    public void setTwoMaterial3(Integer twoMaterial3) {
        this.twoMaterial3 = twoMaterial3;
    }

    public Integer getTwoSum() {
        return twoSum;
    }

    public void setTwoSum(Integer twoSum) {
        this.twoSum = twoSum;
    }

    public Integer getThreeMaterial1() {
        return threeMaterial1;
    }

    public void setThreeMaterial1(Integer threeMaterial1) {
        this.threeMaterial1 = threeMaterial1;
    }

    public Integer getThreeMaterial2() {
        return threeMaterial2;
    }

    public void setThreeMaterial2(Integer threeMaterial2) {
        this.threeMaterial2 = threeMaterial2;
    }

    public Integer getThreeMaterial3() {
        return threeMaterial3;
    }

    public void setThreeMaterial3(Integer threeMaterial3) {
        this.threeMaterial3 = threeMaterial3;
    }

    public Integer getThreeSum() {
        return threeSum;
    }

    public void setThreeSum(Integer threeSum) {
        this.threeSum = threeSum;
    }

    public Integer getFourMaterial1() {
        return fourMaterial1;
    }

    public void setFourMaterial1(Integer fourMaterial1) {
        this.fourMaterial1 = fourMaterial1;
    }

    public Integer getFourMaterial2() {
        return fourMaterial2;
    }

    public void setFourMaterial2(Integer fourMaterial2) {
        this.fourMaterial2 = fourMaterial2;
    }

    public Integer getFourMaterial3() {
        return fourMaterial3;
    }

    public void setFourMaterial3(Integer fourMaterial3) {
        this.fourMaterial3 = fourMaterial3;
    }

    public Integer getFourSum() {
        return fourSum;
    }

    public void setFourSum(Integer fourSum) {
        this.fourSum = fourSum;
    }

    public Integer getFiveMaterial1() {
        return fiveMaterial1;
    }

    public void setFiveMaterial1(Integer fiveMaterial1) {
        this.fiveMaterial1 = fiveMaterial1;
    }

    public Integer getFiveMaterial2() {
        return fiveMaterial2;
    }

    public void setFiveMaterial2(Integer fiveMaterial2) {
        this.fiveMaterial2 = fiveMaterial2;
    }

    public Integer getFiveMaterial3() {
        return fiveMaterial3;
    }

    public void setFiveMaterial3(Integer fiveMaterial3) {
        this.fiveMaterial3 = fiveMaterial3;
    }

    public Integer getFiveSum() {
        return fiveSum;
    }

    public void setFiveSum(Integer fiveSum) {
        this.fiveSum = fiveSum;
    }

    public Integer getSixMaterial1() {
        return sixMaterial1;
    }

    public void setSixMaterial1(Integer sixMaterial1) {
        this.sixMaterial1 = sixMaterial1;
    }

    public Integer getSixMaterial2() {
        return sixMaterial2;
    }

    public void setSixMaterial2(Integer sixMaterial2) {
        this.sixMaterial2 = sixMaterial2;
    }

    public Integer getSixMaterial3() {
        return sixMaterial3;
    }

    public void setSixMaterial3(Integer sixMaterial3) {
        this.sixMaterial3 = sixMaterial3;
    }

    public Integer getSixSum() {
        return sixSum;
    }

    public void setSixSum(Integer sixSum) {
        this.sixSum = sixSum;
    }
}
