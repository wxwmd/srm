package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 供应商技术、厂址变更实体类
 */

public class SupplierChange extends BaseModel {
    /**
     * 序号
     */
    private Integer id;
    /**
     * 流程流水号
     */
    private String serialNo;
    /**
     * 供应商ID
     */
    private String supplierCode;
    /**
     * 变更标题
     */
    private String title;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 二级界面文件地址
     */
    private String documentUrl;

    /**
     * 二级界面文件名称
     */
    private String documentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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
