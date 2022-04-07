package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 技术图档资料
 * 物料号（查询条件）
 */
public class TechnicalData extends BaseModel {

    /**
     * 自动生成
     */
    private Integer id;

    /**
     * 序列号
     */
    private String seriesNumber;

    /**
     * 文档名称
     */
    private String name;

    /**
     * 物料号
     */
    private String materialNumber;

    /**
     * 物料描述
     */
    private String description;

    /**
     * 文档类型
     */
    private String documentType;

    /**
     * 凭证号
     */
    private String certificateNumber;

    /**
     * 凭证版本
     */
    private String certificateVersions;

    /**
     * 凭证部分
     */
    private String certificateDescription;

    /**
     * 是否有效 1：无效 2:有效
     */
    private Integer isEffectivity;

    /**
     * 生效日期
     */
    private String effectiveDate;

    /**
     * 大小量纲
     */
    private String topicOutline;

    /**
     * 可见性（0：部分供应商，1：全部供应商，2：企业）
     */
    private Integer visible;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 二级界面文件地址
     */
    private String documentUrl;

    /**
     * 二级界面文件名称
     */
    private String documentName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateVersions() {
        return certificateVersions;
    }

    public void setCertificateVersions(String certificateVersions) {
        this.certificateVersions = certificateVersions;
    }

    public String getCertificateDescription() {
        return certificateDescription;
    }

    public void setCertificateDescription(String certificateDescription) {
        this.certificateDescription = certificateDescription;
    }

    public Integer getIsEffectivity() {
        return isEffectivity;
    }

    public void setIsEffectivity(Integer isEffectivity) {
        this.isEffectivity = isEffectivity;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getTopicOutline() {
        return topicOutline;
    }

    public void setTopicOutline(String topicOutline) {
        this.topicOutline = topicOutline;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
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
