package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:53
 * @description 常用资料
 * <p>
 * 资料名称（查询条件）
 * 资料描述（查询条件）
 */
public class FrequentlyUsedData extends BaseModel {

    /**
     * 自动生成
     */
    private Integer id;

    /**
     * 资料名称
     */
    private String name;
    /**
     * 资料描述
     */
    private String description;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 可见性（0：部分供应商，1：全部供应商，2：企业）默认企业可见
     */
    private Integer visible;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
