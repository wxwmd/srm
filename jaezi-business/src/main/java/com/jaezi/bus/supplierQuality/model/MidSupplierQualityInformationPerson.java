package com.jaezi.bus.supplierQuality.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 15:31
 * @description
 * 供应商质量信息人员实体类
 */
public class MidSupplierQualityInformationPerson extends BaseModel {

    private Integer id;

    /**
     * 可见人员id
     */
    private Integer personId;

    /**
     * 供应商质量信息id
     */
    private Integer supplierQualityInformationId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getSupplierQualityInformationId() {
        return supplierQualityInformationId;
    }

    public void setSupplierQualityInformationId(Integer supplierQualityInformationId) {
        this.supplierQualityInformationId = supplierQualityInformationId;
    }
}
