package com.jaezi.bus.supplierQuality.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/19 11:14
 * @description
 * QM反馈单人员关系的实体类
 */
public class MidQmFeedbackTablePerson extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     * 可见人员id
     */
    private Integer personId;

    /**
     * QM反馈单id
     */
    private Integer qmFeedbackTableId;

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

    public Integer getQmFeedbackTableId() {
        return qmFeedbackTableId;
    }

    public void setQmFeedbackTableId(Integer qmFeedbackTableId) {
        this.qmFeedbackTableId = qmFeedbackTableId;
    }
}
