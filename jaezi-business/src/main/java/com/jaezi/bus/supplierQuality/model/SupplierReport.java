package com.jaezi.bus.supplierQuality.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/13 10:37
 * @description
 * 供应商报告实体类
 */
public class SupplierReport extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     *报告名称
     */
    private String reportName;

    /**
     *报告描述
     */
    private String reportDescription;

    /**
     *更新时间
     */
    private String updateDate;

    /**
     *物料号
     */
    private String materialNumber;

    /**
     *报告单号
     */
    private String reportNumber;

    /**
     *报告上传时间
     */
    private String reportUploadDate;

    /**
     *报告类型（1：供应商实验报告。2：出厂报告）
     */
    private Integer reportType;

    /**
     *文件地址
     */
    private String url;

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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getReportUploadDate() {
        return reportUploadDate;
    }

    public void setReportUploadDate(String reportUploadDate) {
        this.reportUploadDate = reportUploadDate;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
