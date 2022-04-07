package com.jaezi.bus.supplierQuality.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/13 10:43
 * @description
 * QM反馈单实体类
 */
public class QmFeedbackTable extends BaseModel {

    /**
     * id
     */
    private Integer id;

    /**
     * 问题单号
     */
    private String problemOdd;

    /**
     * 反馈主题
     */
    private String feedbackTheme;

    /**
     * 反馈人
     */
    private String feedbackPeople;

    /**
     * 反馈提示时间
     */
    private String feedbackSubmitDate;

    /**
     * 反馈部门
     */
    private String feedbackDepartment;

    /**
     * 单据类型
     */
    private String receiptType;

    /**
     * 成品代码
     */
    private String finishedProductCode;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 可见性（0：部分供应商，1：全部供应商，2：企业）
     */
    private Integer visible;

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblemOdd() {
        return problemOdd;
    }

    public void setProblemOdd(String problemOdd) {
        this.problemOdd = problemOdd;
    }

    public String getFeedbackTheme() {
        return feedbackTheme;
    }

    public void setFeedbackTheme(String feedbackTheme) {
        this.feedbackTheme = feedbackTheme;
    }

    public String getFeedbackPeople() {
        return feedbackPeople;
    }

    public void setFeedbackPeople(String feedbackPeople) {
        this.feedbackPeople = feedbackPeople;
    }

    public String getFeedbackSubmitDate() {
        return feedbackSubmitDate;
    }

    public void setFeedbackSubmitDate(String feedbackSubmitDate) {
        this.feedbackSubmitDate = feedbackSubmitDate;
    }

    public String getFeedbackDepartment() {
        return feedbackDepartment;
    }

    public void setFeedbackDepartment(String feedbackDepartment) {
        this.feedbackDepartment = feedbackDepartment;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getFinishedProductCode() {
        return finishedProductCode;
    }

    public void setFinishedProductCode(String finishedProductCode) {
        this.finishedProductCode = finishedProductCode;
    }
}
