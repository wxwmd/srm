package com.jaezi.bus.purchase.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 再计划时间确认实体类
 */

public class ScheduleConfirmation extends BaseModel {
    /**
     * 序列
     */
    @ExcelIgnore
    private Integer id;
    /**
     * 工厂
     */
    @ExcelProperty("工厂")
    private String plant;
    /**
     * 物料号
     */
    @ExcelProperty("物料号")
    private String materialNumber;
    /**
     * 物料描述
     */
    @ExcelProperty("物料描述")
    private String materialDescription;
    /**
     * 周期
     */
    @ExcelProperty("周期")
    private Integer period;
    /**
     * 创建日期
     */
    @ExcelProperty("创建日期")
    private String createDate;
    /**
     * 收货日期
     */
    @ExcelProperty("收货日期")
    private String receDate;
    /**
     * 再计划日期
     */
    @ExcelProperty("再计划日期")
    private String rescheduledDate;
    /**
     * 落实日期
     */
    @ExcelProperty("落实日期")
    private String carryOutTheDate;
    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;
    /**
     * 交货点日期
     */
    @ExcelProperty("交货点日期")
    private String deliveryDate;
    /**
     * 采购订单
     */
    @ExcelProperty("采购订单")
    private String pOrder;
    /**
     * 项目
     */
    @ExcelProperty("项目")
    private String project;
    /**
     * 订单数量
     */
    @ExcelProperty("订单数量")
    private BigDecimal orderQty;
    /**
     * 确定订单比率
     */
    @ExcelProperty("确定订单比率")
    private Integer determineOrderRatio;
    /**
     * 供应商编码
     */
    @ExcelProperty("供应商编码")
    private String supplierCode;

    /**
     * 类型 提前 取消
     */
    @ExcelProperty("类型")
    private String planType;

    /**
     * 取消状态 可取消 不可取消
     */
    @ExcelProperty("取消状态")
    private String cancelStatus;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReceDate() {
        return receDate;
    }

    public void setReceDate(String receDate) {
        this.receDate = receDate;
    }

    public String getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(String rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
    }

    public String getCarryOutTheDate() {
        return carryOutTheDate;
    }

    public void setCarryOutTheDate(String carryOutTheDate) {
        this.carryOutTheDate = carryOutTheDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getpOrder() {
        return pOrder;
    }

    public void setpOrder(String pOrder) {
        this.pOrder = pOrder;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getDetermineOrderRatio() {
        return determineOrderRatio;
    }

    public void setDetermineOrderRatio(Integer determineOrderRatio) {
        this.determineOrderRatio = determineOrderRatio;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }
}

