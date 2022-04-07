package com.jaezi.bus.direct.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 直送装车单实体类
 */

public class LoadingDirectDelivery extends BaseModel {
    /**
     * 直送装车单ID
     */
    @ExcelIgnore
    private Integer id;
    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;
    /**
     * 工厂
     */
    @ExcelProperty(value = "工厂")
    private String plant;
    /**
     * 供应商代码
     */
    @ExcelProperty(value = "供应商编码")
    private String supplierCode;
    /**
     * 到货日期
     */
    @ExcelProperty(value = "到货日期")
    private String arrivalDate;
    /**
     * 车牌号
     */
    @ExcelProperty(value = "车牌号")
    private String licensePlateNumber;
    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String phone;

    /**
     * 生成日期
     */
    @ExcelProperty(value = "生成日期")
    private String createDate;
    /**
     * 装车单号
     */
    @ExcelProperty(value = "装车单号")
    private String loadingNumber;
    /**
     * 收货窗口
     */
    @ExcelIgnore
    private String receivingWindow;
    /**
     * 库位
     */
    @ExcelIgnore
    private String location;
    /**
     * 需求信息发布人
     */
    @ExcelIgnore
    private String needInformationPublisher;
    /**
     * 上线使用时间
     */
    @ExcelIgnore
    private String onlineServiceTime;
    /**
     * 产线
     */
    @ExcelIgnore
    private String productionLine;
    /**
     * 供应部联系人
     */
    @ExcelIgnore
    private String supplyDepartmentContactPerson;
    /**
     * 需求信息发布人电话
     */
    @ExcelIgnore
    private String needInformationPublisherPhone;

    public String getNeedInformationPublisherPhone() {
        return needInformationPublisherPhone;
    }

    public void setNeedInformationPublisherPhone(String needInformationPublisherPhone) {
        this.needInformationPublisherPhone = needInformationPublisherPhone;
    }

    public String getSupplyDepartmentContactPerson() {
        return supplyDepartmentContactPerson;
    }

    public void setSupplyDepartmentContactPerson(String supplyDepartmentContactPerson) {
        this.supplyDepartmentContactPerson = supplyDepartmentContactPerson;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getReceivingWindow() {
        return receivingWindow;
    }

    public void setReceivingWindow(String receivingWindow) {
        this.receivingWindow = receivingWindow;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNeedInformationPublisher() {
        return needInformationPublisher;
    }

    public void setNeedInformationPublisher(String needInformationPublisher) {
        this.needInformationPublisher = needInformationPublisher;
    }

    public String getOnlineServiceTime() {
        return onlineServiceTime;
    }

    public void setOnlineServiceTime(String onlineServiceTime) {
        this.onlineServiceTime = onlineServiceTime;
    }

    public String getLoadingNumber() {
        return loadingNumber;
    }

    public void setLoadingNumber(String loadingNumber) {
        this.loadingNumber = loadingNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
