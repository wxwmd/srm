package com.jaezi.bus.purchase.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 9:26
 * @description
 * 装车单实体类
 */
public class LoadingDocument extends BaseModel {
    /**
     * Id
     */
    private Integer id;


    /**
     * 装车单号
     */
    private String loadingNumber;


    /**
     * 装车单生成日期
     */
    private String loadingDocumentDate;

    /**
     * 供应商编码
     */
    private String supplierCode;


    /**
     * 装车方式
     */
    private Integer loadingWay;

    /**
     * 收货窗口
     */
    private String receivingWindow;

    /**
     * 装运说明
     */
    private String shippingExplain;

    /**
     * 装车日期
     */
    private String loadingDate;

    /**
     * 计划到货日期
     */
    private String planDeliveryDate;

    /**
     * 联系方式
     */
    private String logisticsContactInformation;

    /**
     * 装车发运日期
     */
    private String loadingShipmentDate;

    /**
     * 工厂
     */
    private String factory;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 车牌号
     */
    private String procurementUnit;

    private String procurementUnitAddress;

    public String getProcurementUnit() {
        return procurementUnit;
    }

    public void setProcurementUnit(String procurementUnit) {
        this.procurementUnit = procurementUnit;
    }

    public String getProcurementUnitAddress() {
        return procurementUnitAddress;
    }

    public void setProcurementUnitAddress(String procurementUnitAddress) {
        this.procurementUnitAddress = procurementUnitAddress;
    }

    public String getReceivingWindow() {
        return receivingWindow;
    }

    public void setReceivingWindow(String receivingWindow) {
        this.receivingWindow = receivingWindow;
    }

    public String getShippingExplain() {
        return shippingExplain;
    }

    public void setShippingExplain(String shippingExplain) {
        this.shippingExplain = shippingExplain;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getLoadingDocumentDate() {
        return loadingDocumentDate;
    }

    public void setLoadingDocumentDate(String loadingDocumentDate) {
        this.loadingDocumentDate = loadingDocumentDate;
    }

    public String getLoadingNumber() {
        return loadingNumber;
    }

    public void setLoadingNumber(String loadingNumber) {
        this.loadingNumber = loadingNumber;
    }

    public Integer getLoadingWay() {
        return loadingWay;
    }

    public void setLoadingWay(Integer loadingWay) {
        this.loadingWay = loadingWay;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getLoadingShipmentDate() {
        return loadingShipmentDate;
    }

    public void setLoadingShipmentDate(String loadingShipmentDate) {
        this.loadingShipmentDate = loadingShipmentDate;
    }

    public String getLogisticsContactInformation() {
        return logisticsContactInformation;
    }

    public void setLogisticsContactInformation(String logisticsContactInformation) {
        this.logisticsContactInformation = logisticsContactInformation;
    }
}
