package com.jaezi.bus.inventory.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/11/15  14:33:20
 * @description 寄售物资结收发存详情实体类
 */
public class ConsignmentGoodsSDTRParticular extends BaseModel {
    /**
     * 寄售物资结收发存详情ID
     */
    @ExcelIgnore
    private Integer id;
    /**
     * 单据号
     */
    @ExcelProperty(value = "单据号")
    private Integer orderNo;
    /**
     * 行号
     */
    @ExcelProperty(value = "行号")
    private Integer line;
    /**
     * 记账日期
     */
    @ExcelProperty(value = "记账日期")
    private String bookedDate;
    /**
     * 收发标志
     */
    @ExcelProperty(value = "收发标志")
    private String shippingMark;
    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private BigDecimal qty;
    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal money;

    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号")
    private String materialNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getShippingMark() {
        return shippingMark;
    }

    public void setShippingMark(String shippingMark) {
        this.shippingMark = shippingMark;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }
}
