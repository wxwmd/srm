package com.jaezi.synergia.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3  18:59:58
 * @description
 */
public class MidTechnicalInformationSupplier extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     * 技术资料id
     */
    private Integer informationId;

    /**
     * 人员id
     */
    private Integer personId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

}
