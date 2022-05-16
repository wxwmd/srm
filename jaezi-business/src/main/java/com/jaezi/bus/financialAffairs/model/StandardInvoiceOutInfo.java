package com.jaezi.bus.financialAffairs.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author wxw
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/5/16
 * @description
 */
public class StandardInvoiceOutInfo extends BaseModel {

    private Integer id;

    /**
     * 序号
     */
    private Integer serialNumber;

    /**
     * 采购订单
     */
    private String purchaseOrder;

    /**
     * 行项目
     */
    private String hongProject;

    /**
     * 物料凭证
     */
    private String materialVoucher;

    /**
     * 凭证项目
     */
    private String voucherProject;

    /**
     * 物料
     */
    private String material;

    /**
     * 物料描述
     */
    private String materialName;

    /**
     * 临时发票号
     */
    private Integer interimInvoiceNumber;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 数量
     */
    private BigDecimal quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getHongProject() {
        return hongProject;
    }

    public void setHongProject(String hongProject) {
        this.hongProject = hongProject;
    }

    public String getMaterialVoucher() {
        return materialVoucher;
    }

    public void setMaterialVoucher(String materialVoucher) {
        this.materialVoucher = materialVoucher;
    }

    public String getVoucherProject() {
        return voucherProject;
    }

    public void setVoucherProject(String voucherProject) {
        this.voucherProject = voucherProject;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getInterimInvoiceNumber() {
        return interimInvoiceNumber;
    }

    public void setInterimInvoiceNumber(Integer interimInvoiceNumber) {
        this.interimInvoiceNumber = interimInvoiceNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StandardInvoiceOutInfo{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", purchaseOrder='" + purchaseOrder + '\'' +
                ", hongProject='" + hongProject + '\'' +
                ", materialVoucher='" + materialVoucher + '\'' +
                ", voucherProject='" + voucherProject + '\'' +
                ", material='" + material + '\'' +
                ", materialName='" + materialName + '\'' +
                ", interimInvoiceNumber=" + interimInvoiceNumber +
                ", supplierCode='" + supplierCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
