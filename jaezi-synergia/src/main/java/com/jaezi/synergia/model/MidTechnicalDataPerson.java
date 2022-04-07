package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/4 14:53
 * @description
 */
public class MidTechnicalDataPerson extends BaseModel {


    private Integer id;

    /**
     * 人员Id
     */
    private Integer personId;

    /**
     * 技术图档Id
     */
    private Integer technicalDataId;

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

    public Integer getTechnicalDataId() {
        return technicalDataId;
    }

    public void setTechnicalDataId(Integer technicalDataId) {
        this.technicalDataId = technicalDataId;
    }
}
