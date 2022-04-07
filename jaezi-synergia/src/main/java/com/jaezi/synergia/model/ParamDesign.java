package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 物料参数设计管理信息实体类
 */

public class ParamDesign extends BaseModel {

    /**
     * 物料参数ID
     */
    private Integer id;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 填写人
     */
    private String writtenBy;

    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 单据号
     */
    private Integer documentNo;

    /**
     * 物料编码
     */
    private String code;

    /**
     * 物料名称
     */
    private String name;

    /**
     * 拆分件
     */
    private String splitA;

    /**
     * 单台使用数量
     */
    private Integer noPerUnit;

    /**
     * 是否易损易耗
     */
    private Integer isLossy;

    /**
     * 失效修复方式
     */
    private Integer failureRepairMode;

    /**
     * 设计使用寿命
     */
    private Integer designServiceLife;

    /**
     * 寿命单位
     */
    private Integer lifeUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(Integer documentNo) {
        this.documentNo = documentNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSplitA() {
        return splitA;
    }

    public void setSplitA(String splitA) {
        this.splitA = splitA;
    }

    public Integer getNoPerUnit() {
        return noPerUnit;
    }

    public void setNoPerUnit(Integer noPerUnit) {
        this.noPerUnit = noPerUnit;
    }

    public Integer getIsLossy() {
        return isLossy;
    }

    public void setIsLossy(Integer isLossy) {
        this.isLossy = isLossy;
    }

    public Integer getFailureRepairMode() {
        return failureRepairMode;
    }

    public void setFailureRepairMode(Integer failureRepairMode) {
        this.failureRepairMode = failureRepairMode;
    }

    public Integer getDesignServiceLife() {
        return designServiceLife;
    }

    public void setDesignServiceLife(Integer designServiceLife) {
        this.designServiceLife = designServiceLife;
    }

    public Integer getLifeUnit() {
        return lifeUnit;
    }

    public void setLifeUnit(Integer lifeUnit) {
        this.lifeUnit = lifeUnit;
    }
}
