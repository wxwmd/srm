package com.jaezi.bus.financialAffairs.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9  17:25:15
 * @description
 * 标准物资开票
 */
public class StandardInvoiceOut extends BaseModel {


    @ExcelIgnore
    private Integer id;

    /**
     * 供应商编码
     */
    @ExcelProperty("供应商编码")
    private String supplierCode;

    @ExcelProperty("供应商名称")
    private String supplierName;


    /**
     * 物料
     */
    @ExcelProperty("物料号")
    private String material;

    @ExcelProperty("物料名称")
    private String materialName;

    /**
     * 采购订单
     */
    @ExcelProperty("采购订单")
    private String purchaseOrder;


    /**
     * 行项目
     */
    @ExcelProperty("行项目")
    private String hongProject;


    /**
     * 工厂
     */
    @ExcelProperty("工厂")
    private Integer plant;

    /**
     * 物料凭证
     */
    @ExcelProperty("物料凭证")
    private String materialVoucher;

    /**
     * 凭证项目
     */
    @ExcelProperty("凭证项目")
    private String voucherProject;

    /**
     * 未开票数量
     */
    @ExcelProperty("未开票数量")
    private BigDecimal notOutInvoiceNumber;

    /**
     * 冻结数量
     */
    @ExcelProperty("冻结数量")
    private BigDecimal freezeNumber;

    /**
     * 顺序
     */
    @ExcelIgnore
    private String order;


    /**
     * 物料描述
     */
    @ExcelIgnore
    private String materialDescribe;


    /**
     * 临时发票号
     */
    @ExcelIgnore
    private Integer interimInvoiceNumber;

    /**
     * 总金额（不含税）
     */
    @ExcelIgnore
    private BigDecimal withoutTaxAmount;

    /*
    * 税额
    * */
    @ExcelIgnore
    private BigDecimal taxAmount;

    /*
    * 价税合计
    * */
    @ExcelIgnore
    private BigDecimal totalAmount;

    /**
     * 单价
     */
    @ExcelIgnore
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    @ExcelProperty("数量")
    private BigDecimal quantity;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private String createTime;


    /*
    * 是否可以开票
    * -1 :ok
    * 否则：不行
    * */
    @ExcelIgnore()
    private Integer status;


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getFreezeNumber() {
        return freezeNumber;
    }

    public void setFreezeNumber(BigDecimal freezeNumber) {
        this.freezeNumber = freezeNumber;
    }


    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlant() {
        return plant;
    }

    public void setPlant(Integer plant) {
        this.plant = plant;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getHongProject() {
        return hongProject;
    }

    public void setHongProject(String hongProject) {
        this.hongProject = hongProject;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getInterimInvoiceNumber() {
        return interimInvoiceNumber;
    }

    public void setInterimInvoiceNumber(Integer interimInvoiceNumber) {
        this.interimInvoiceNumber = interimInvoiceNumber;
    }

    public BigDecimal getWithoutTaxAmount() {
        return withoutTaxAmount;
    }

    public void setWithoutTaxAmount(BigDecimal withoutTaxAmount) {
        this.withoutTaxAmount = withoutTaxAmount;
    }

    public BigDecimal getNotOutInvoiceNumber() {
        return notOutInvoiceNumber;
    }

    public void setNotOutInvoiceNumber(BigDecimal notOutInvoiceNumber) {
        this.notOutInvoiceNumber = notOutInvoiceNumber;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StandardInvoiceOut{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", material='" + material + '\'' +
                ", materialName='" + materialName + '\'' +
                ", purchaseOrder='" + purchaseOrder + '\'' +
                ", hongProject='" + hongProject + '\'' +
                ", plant=" + plant +
                ", materialVoucher='" + materialVoucher + '\'' +
                ", voucherProject='" + voucherProject + '\'' +
                ", notOutInvoiceNumber=" + notOutInvoiceNumber +
                ", freezeNumber=" + freezeNumber +
                ", order='" + order + '\'' +
                ", materialDescribe='" + materialDescribe + '\'' +
                ", interimInvoiceNumber=" + interimInvoiceNumber +
                ", withoutTaxAmount=" + withoutTaxAmount +
                ", taxAmount=" + taxAmount +
                ", totalAmount=" + totalAmount +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                '}';
    }
}
