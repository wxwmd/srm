package com.jaezi.bus.salesreturn.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 退货确认实体类
 */

public class ReturnConfirmation extends BaseModel {
    /**
     * 退货确认ID
     */
    private Integer id;
    /**
     * 退货确认单状态
     */
    private String status;
    /**
     * 物料号
     */
    private String materialNumber;
    /**
     * 物料描述
     */
    private String materialDescription;
    /**
     * 采购类型
     */
    private String pType;
    /**
     * 创建日期
     */
    private String createDate;
    /**
     * 工厂
     */
    private String plant;
    /**
     * 申请退货数量
     */
    private BigDecimal applyQty;
    /**
     * 确认退货数量
     */
    private BigDecimal confirmQty;
    /**
     * 单位
     */
    private String unit;
    /**
     * 未开票采购订单
     */
    private String unPurchaseOrder;
    /**
     * 实物拉走方式
     */
    private Integer modeTransport;
    /**
     * 快递公司
     */
    private String expressCompany;
    /**
     * 收货地址
     */
    private String shippingAddress;
    /**
     * 收货人姓名
     */
    private String consigneeName;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 供应商编码
     */
    private String supplierCode;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public BigDecimal getApplyQty() {
        return applyQty;
    }

    public void setApplyQty(BigDecimal applyQty) {
        this.applyQty = applyQty;
    }

    public BigDecimal getConfirmQty() {
        return confirmQty;
    }

    public void setConfirmQty(BigDecimal confirmQty) {
        this.confirmQty = confirmQty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnPurchaseOrder() {
        return unPurchaseOrder;
    }

    public void setUnPurchaseOrder(String unPurchaseOrder) {
        this.unPurchaseOrder = unPurchaseOrder;
    }

    public Integer getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(Integer modeTransport) {
        this.modeTransport = modeTransport;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
