package com.jaezi.synergia.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.util.List;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 16:16
 * @description 索赔清单
 * <p>
 * 供应商维护时间（查询条件）
 * 服务单号（查询条件）
 * 是否付款（查询条件）
 */
public class ClaimNote extends BaseModel {

    private String id;

    /**
     * 服务单号
     */
    private String serverNumber;

    /**
     * 材料费
     */
    private Double materialsExpense;

    /**
     * 工时费
     */
    private Double manHourExpense;

    /**
     * 差旅费
     */
    private Double travelExpense;

    /**
     * 其他费用
     */
    private Double otherExpenses;

    /**
     * 合计
     */
    private Double amount;

    /**
     * 是否付款  0:未付款  1:已付款
     */
    private Integer isPay;

    /**
     * 关联信息id
     */
    private Integer infoId;

    /**
     * 关联回复信息
     */
    private List<Info> infoList;

    private String supplierCode;

    private Integer userType;

    private Long createTime;

    private String createUser;

    /**
     * 二级界面文件地址
     */
    private String documentUrl;

    /**
     * 二级界面文件名称
     */
    private String documentName;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerNumber() {
        return serverNumber;
    }

    public void setServerNumber(String serverNumber) {
        this.serverNumber = serverNumber;
    }

    public Double getMaterialsExpense() {
        return materialsExpense;
    }

    public void setMaterialsExpense(Double materialsExpense) {
        this.materialsExpense = materialsExpense;
    }

    public Double getManHourExpense() {
        return manHourExpense;
    }

    public void setManHourExpense(Double manHourExpense) {
        this.manHourExpense = manHourExpense;
    }

    public Double getTravelExpense() {
        return travelExpense;
    }

    public void setTravelExpense(Double travelExpense) {
        this.travelExpense = travelExpense;
    }

    public Double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(Double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
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
