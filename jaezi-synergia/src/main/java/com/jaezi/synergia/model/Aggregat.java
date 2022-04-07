package com.jaezi.synergia.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 16:53
 * @description 总成件组件
 * <p>
 * 物料号（查询条件）
 */
public class Aggregat extends BaseModel {

    /**
     * id 自动生成
     */
    @ExcelIgnore
    private String id;
    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号")
    private String materialNumber;

    /**
     * 物料描述
     */
    @ExcelProperty(value = "物料描述")
    private String materialDescription;

    /**
     * 组件数量
     */
    @ExcelProperty(value = "组件数量")
    private String componentNumber;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String unit;

    /**
     * 物料类型
     */
    @ExcelProperty(value = "物料类型")
    private Integer materialType;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private String amount;

    /**
     * PGr
     */
    @ExcelProperty(value = "PGr")
    private String pgr;

    /**
     * 二级界面文件地址
     */
    @ExcelIgnore
    private String documentUrl;

    /**
     * 二级界面文件名称
     */
    @ExcelIgnore
    private String documentName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComponentNumber() {
        return componentNumber;
    }

    public void setComponentNumber(String componentNumber) {
        this.componentNumber = componentNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public String getAmount() {
        return amount;
    }

    public String getPgr() {
        return pgr;
    }

    public void setPgr(String pgr) {
        this.pgr = pgr;
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
