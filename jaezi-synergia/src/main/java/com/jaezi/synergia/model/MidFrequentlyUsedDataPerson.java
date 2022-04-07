package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3 10:44
 * @description
 */
public class MidFrequentlyUsedDataPerson extends BaseModel {

    private Integer id;

    /**
     * 人员Id
     */
    private Integer personId;

    /**
     * 常用资料Id
     */
    private Integer frequentlyUsedDataId;

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

    public Integer getFrequentlyUsedDataId() {
        return frequentlyUsedDataId;
    }

    public void setFrequentlyUsedDataId(Integer frequentlyUsedDataId) {
        this.frequentlyUsedDataId = frequentlyUsedDataId;
    }
}
