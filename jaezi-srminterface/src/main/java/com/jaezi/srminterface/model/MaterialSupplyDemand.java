package com.jaezi.srminterface.model;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 物料供需中间表
 */
public class MaterialSupplyDemand {
    /**
     * 计划批号
     */
    private String planLotNo;
    /**
     * 品号
     */
    private String materialNumber;
    /**
     * 单位
     */
    private String unit;
    /**
     * 品名
     */
    private String materialName;
    /**
     * 规格
     */
    private String specification;
    /**
     * 间距日期
     */
    private String distanceDate;
    /**
     * 依据
     */
    private String gist;
    /**
     * 供需
     */
    private String supplyDemand;
    /**
     * 供需日期
     */
    private String supplyDemandDate;
    /**
     * 来源
     */
    private String source;
    /**
     * 数量
     */
    private BigDecimal qty;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 供应商
     */
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPlanLotNo() {
        return planLotNo;
    }

    public void setPlanLotNo(String planLotNo) {
        this.planLotNo = planLotNo;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDistanceDate() {
        return distanceDate;
    }

    public void setDistanceDate(String distanceDate) {
        this.distanceDate = distanceDate;
    }

    public String getGist() {
        return gist;
    }

    public void setGist(String gist) {
        this.gist = gist;
    }

    public String getSupplyDemand() {
        return supplyDemand;
    }

    public void setSupplyDemand(String supplyDemand) {
        this.supplyDemand = supplyDemand;
    }

    public String getSupplyDemandDate() {
        return supplyDemandDate;
    }

    public void setSupplyDemandDate(String supplyDemandDate) {
        this.supplyDemandDate = supplyDemandDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
