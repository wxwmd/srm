package com.jaezi.common.bean;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/5/7 18:03
 * @description
 */
public class MiddleObject {

    private Integer leftId;
    private Integer rightId;
    private Integer status;
    private Integer grade;
    private String remark;

    public MiddleObject() {
    }

    public MiddleObject(Integer leftId, Integer rightId) {
        this.leftId = leftId;
        this.rightId = rightId;
    }

    public Integer getLeftId() {
        return leftId;
    }

    public void setLeftId(Integer leftId) {
        this.leftId = leftId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
