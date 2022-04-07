package com.jaezi.bus.plan.dto;

import com.jaezi.bus.plan.model.JunDemandPlanning;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 六月需求计划扩展类
 */

public class JunDemandPlanningDto {
    /**
     * 产品需求
     */
    private Integer zeroMaterial;

    /**
     * 产品需求
     */
    private Integer oneMaterial;
    /**
     * 产品需求
     */
    private Integer twoMaterial;
    /**
     * 产品需求
     */
    private Integer threeMaterial;
    /**
     * 产品需求
     */
    private Integer fourMaterial;
    /**
     * 产品需求
     */
    private Integer fiveMaterial;
    /**
     * 产品需求
     */
    private Integer sixMaterial;

    public Integer getZeroMaterial() {
        return zeroMaterial;
    }

    public void setZeroMaterial(Integer zeroMaterial) {
        this.zeroMaterial = zeroMaterial;
    }

    public Integer getOneMaterial() {
        return oneMaterial;
    }

    public void setOneMaterial(Integer oneMaterial) {
        this.oneMaterial = oneMaterial;
    }

    public Integer getTwoMaterial() {
        return twoMaterial;
    }

    public void setTwoMaterial(Integer twoMaterial) {
        this.twoMaterial = twoMaterial;
    }

    public Integer getThreeMaterial() {
        return threeMaterial;
    }

    public void setThreeMaterial(Integer threeMaterial) {
        this.threeMaterial = threeMaterial;
    }

    public Integer getFourMaterial() {
        return fourMaterial;
    }

    public void setFourMaterial(Integer fourMaterial) {
        this.fourMaterial = fourMaterial;
    }

    public Integer getFiveMaterial() {
        return fiveMaterial;
    }

    public void setFiveMaterial(Integer fiveMaterial) {
        this.fiveMaterial = fiveMaterial;
    }

    public Integer getSixMaterial() {
        return sixMaterial;
    }

    public void setSixMaterial(Integer sixMaterial) {
        this.sixMaterial = sixMaterial;
    }

}
