package com.jaezi.bus.inventory.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10 17:18
 * @description
 * 寄售物资结算通知单实体类
 */
public class ConsignmentGoodsCloseNoticeLetter extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     * 工厂
     */
    private String factory;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 期间
     */
    private String period;

    /**
     * 报表日期
     */
    private String reportDate;

    /**
     * 特别提示
     */
    private String specialSuggestion;

    /**
     * 序号
     */
    private Integer number;

    /**
     * 物料号
     */
    private Integer materialNumber;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 数量
     */
    private  Integer count;

    /**
     * 供应商编码
     */
    private String supplierCode;


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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getSpecialSuggestion() {
        return specialSuggestion;
    }

    public void setSpecialSuggestion(String specialSuggestion) {
        this.specialSuggestion = specialSuggestion;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Integer materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
