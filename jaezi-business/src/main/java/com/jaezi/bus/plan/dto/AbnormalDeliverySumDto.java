package com.jaezi.bus.plan.dto;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 交付异常汇总
 */

public class AbnormalDeliverySumDto {
    private String supplierCode;

    private Integer purchaseYellow;

    private Integer purchaseRed;

    private Integer inventoryYellow;

    private Integer inventoryRed;

    private Integer truckYellow;

    private Integer truckRed;

    private Integer factoryYellow;

    private Integer factoryRed;

    private Integer postponeYellow;

    private Integer postponeRed;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getPurchaseYellow() {
        return purchaseYellow;
    }

    public void setPurchaseYellow(Integer purchaseYellow) {
        this.purchaseYellow = purchaseYellow;
    }

    public Integer getPurchaseRed() {
        return purchaseRed;
    }

    public void setPurchaseRed(Integer purchaseRed) {
        this.purchaseRed = purchaseRed;
    }

    public Integer getInventoryYellow() {
        return inventoryYellow;
    }

    public void setInventoryYellow(Integer inventoryYellow) {
        this.inventoryYellow = inventoryYellow;
    }

    public Integer getInventoryRed() {
        return inventoryRed;
    }

    public void setInventoryRed(Integer inventoryRed) {
        this.inventoryRed = inventoryRed;
    }

    public Integer getTruckYellow() {
        return truckYellow;
    }

    public void setTruckYellow(Integer truckYellow) {
        this.truckYellow = truckYellow;
    }

    public Integer getTruckRed() {
        return truckRed;
    }

    public void setTruckRed(Integer truckRed) {
        this.truckRed = truckRed;
    }

    public Integer getFactoryYellow() {
        return factoryYellow;
    }

    public void setFactoryYellow(Integer factoryYellow) {
        this.factoryYellow = factoryYellow;
    }

    public Integer getFactoryRed() {
        return factoryRed;
    }

    public void setFactoryRed(Integer factoryRed) {
        this.factoryRed = factoryRed;
    }

    public Integer getPostponeYellow() {
        return postponeYellow;
    }

    public void setPostponeYellow(Integer postponeYellow) {
        this.postponeYellow = postponeYellow;
    }

    public Integer getPostponeRed() {
        return postponeRed;
    }

    public void setPostponeRed(Integer postponeRed) {
        this.postponeRed = postponeRed;
    }
}
