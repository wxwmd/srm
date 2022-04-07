package com.jaezi.bus.direct.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 直送装车单明细实体类
 */
public class LoadingDirectDeliveryDetail extends BaseModel {
    /**
     * 直送装车单明细ID
     */
    private Integer id;
    /**
     * 直送装车单明细关联ID
     */
    private Integer rid;
    /**
     * 生产订单
     */
    private String productionOrder;
    /**
     * 车工号
     */
    private String turner;
    /**
     * 工作中心
     */
    private String workCenter;
    /**
     * 物料号
     */
    private String materialNumber;
    /**
     * 物料描述
     */
    private String materialDescription;
    /**
     * 计划数量
     */
    private BigDecimal planQty;
    /**
     * 实收数量
     */
    private BigDecimal actualQty;
    /**
     * 单位
     */
    private String unit;
    /**
     * 工位
     */
    private String station;
    /**
     * 关键物资
     */
    private String keyMaterials;
    /**
     * 报号机号
     */
    private String numberImmediately;

    /**
     * 总成物料
     */
    private String assemblyMaterial;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(String productionOrder) {
        this.productionOrder = productionOrder;
    }

    public String getTurner() {
        return turner;
    }

    public void setTurner(String turner) {
        this.turner = turner;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
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

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getActualQty() {
        return actualQty;
    }

    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getKeyMaterials() {
        return keyMaterials;
    }

    public void setKeyMaterials(String keyMaterials) {
        this.keyMaterials = keyMaterials;
    }

    public String getNumberImmediately() {
        return numberImmediately;
    }

    public void setNumberImmediately(String numberImmediately) {
        this.numberImmediately = numberImmediately;
    }

    public String getAssemblyMaterial() {
        return assemblyMaterial;
    }

    public void setAssemblyMaterial(String assemblyMaterial) {
        this.assemblyMaterial = assemblyMaterial;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
