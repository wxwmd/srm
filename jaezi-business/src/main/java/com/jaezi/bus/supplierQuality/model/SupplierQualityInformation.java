package com.jaezi.bus.supplierQuality.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 15:25
 * @description
 * 供应商质量信息实体类
 */
public class SupplierQualityInformation extends BaseModel {

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
     * 文件地址
     */
    private String url;

    /**
     * 可见性（0：部分供应商，1：全部供应商，2：企业）
     */
    private Integer visible;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
