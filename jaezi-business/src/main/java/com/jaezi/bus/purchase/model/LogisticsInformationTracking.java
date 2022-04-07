package com.jaezi.bus.purchase.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 物流信息跟踪实体类
 */

public class LogisticsInformationTracking extends BaseModel {

    private Integer id;
    /**
     * 装车单号
     */
    private String loadingNumber;
    /**
     * 发运单号
     */
    private String sendTheAwb;
    /**
     * 采购订单号
     */
    private String pOrderNumber;
    /**
     * 供应商代码
     */
    private String supplierNumber;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 货物名称
     */
    private String cargoName;
    /**
     * 数量
     */
    private String qty;
    /**
     * 重量KG
     */
    private String weight;
    /**
     * 到货地点
     */
    private String placeOfArrival;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 装车日期
     */
    private String loadingDate;
    /**
     * 计划到货日期
     */
    private String scheduledArrivalDate;
    /**
     * 发运日期
     */
    private String shipDate;
    /**
     * 装运时间
     */
    private String shipmentTime;
    /**
     * 提货数量（件）
     */
    private Integer extractQty;
    /**
     * 货物重量（吨）
     */
    private Integer cargoWeight;
    /**
     * 车牌号码
     */
    private String plateNumber;
    /**
     * 司机姓名
     */
    private String driverName;
    /**
     * 联系方式
     */
    private String contactWay;
    /**
     * 第一天上午
     */
    private Integer dayOneMorning;
    /**
     * 第一天下午
     */
    private Integer dayOneAfternoon;
    /**
     * 第二天上午
     */
    private Integer dayTwoMorning;
    /**
     * 第二天下午
     */
    private Integer dayTwoAfternoon;
    /**
     * 第三天上午
     */
    private Integer dayThreeMorning;
    /**
     * 第三天下午
     */
    private Integer dayThreeAfternoon;
    /**
     * 第四天上午
     */
    private Integer dayFourMorning;
    /**
     * 第四天下午
     */
    private Integer dayFourAfternoon;
    /**
     * 交货日期
     */
    private String deliveryDate;
    /**
     * 交货时间
     */
    private String deliveryTime;
    /**
     * 晚交付原因
     */
    private String reasonsForLateDelivery;
    /**
     * 丢失或损坏件数
     */
    private Integer defNumber;
    /**
     * 签收人
     */
    private String signer;
    /**
     * KPI(天）
     */
    private Integer kpi;
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

    public String getLoadingNumber() {
        return loadingNumber;
    }

    public void setLoadingNumber(String loadingNumber) {
        this.loadingNumber = loadingNumber;
    }

    public String getSendTheAwb() {
        return sendTheAwb;
    }

    public void setSendTheAwb(String sendTheAwb) {
        this.sendTheAwb = sendTheAwb;
    }

    public String getpOrderNumber() {
        return pOrderNumber;
    }

    public void setpOrderNumber(String pOrderNumber) {
        this.pOrderNumber = pOrderNumber;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPlaceOfArrival() {
        return placeOfArrival;
    }

    public void setPlaceOfArrival(String placeOfArrival) {
        this.placeOfArrival = placeOfArrival;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getScheduledArrivalDate() {
        return scheduledArrivalDate;
    }

    public void setScheduledArrivalDate(String scheduledArrivalDate) {
        this.scheduledArrivalDate = scheduledArrivalDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipmentTime() {
        return shipmentTime;
    }

    public void setShipmentTime(String shipmentTime) {
        this.shipmentTime = shipmentTime;
    }

    public Integer getExtractQty() {
        return extractQty;
    }

    public void setExtractQty(Integer extractQty) {
        this.extractQty = extractQty;
    }

    public Integer getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Integer cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Integer getDayOneMorning() {
        return dayOneMorning;
    }

    public void setDayOneMorning(Integer dayOneMorning) {
        this.dayOneMorning = dayOneMorning;
    }

    public Integer getDayOneAfternoon() {
        return dayOneAfternoon;
    }

    public void setDayOneAfternoon(Integer dayOneAfternoon) {
        this.dayOneAfternoon = dayOneAfternoon;
    }

    public Integer getDayTwoMorning() {
        return dayTwoMorning;
    }

    public void setDayTwoMorning(Integer dayTwoMorning) {
        this.dayTwoMorning = dayTwoMorning;
    }

    public Integer getDayTwoAfternoon() {
        return dayTwoAfternoon;
    }

    public void setDayTwoAfternoon(Integer dayTwoAfternoon) {
        this.dayTwoAfternoon = dayTwoAfternoon;
    }

    public Integer getDayThreeMorning() {
        return dayThreeMorning;
    }

    public void setDayThreeMorning(Integer dayThreeMorning) {
        this.dayThreeMorning = dayThreeMorning;
    }

    public Integer getDayThreeAfternoon() {
        return dayThreeAfternoon;
    }

    public void setDayThreeAfternoon(Integer dayThreeAfternoon) {
        this.dayThreeAfternoon = dayThreeAfternoon;
    }

    public Integer getDayFourMorning() {
        return dayFourMorning;
    }

    public void setDayFourMorning(Integer dayFourMorning) {
        this.dayFourMorning = dayFourMorning;
    }

    public Integer getDayFourAfternoon() {
        return dayFourAfternoon;
    }

    public void setDayFourAfternoon(Integer dayFourAfternoon) {
        this.dayFourAfternoon = dayFourAfternoon;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getReasonsForLateDelivery() {
        return reasonsForLateDelivery;
    }

    public void setReasonsForLateDelivery(String reasonsForLateDelivery) {
        this.reasonsForLateDelivery = reasonsForLateDelivery;
    }

    public Integer getDefNumber() {
        return defNumber;
    }

    public void setDefNumber(Integer defNumber) {
        this.defNumber = defNumber;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Integer getKpi() {
        return kpi;
    }

    public void setKpi(Integer kpi) {
        this.kpi = kpi;
    }
}
