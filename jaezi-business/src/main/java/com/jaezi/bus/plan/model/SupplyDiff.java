package com.jaezi.bus.plan.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 供需差异实体类
 */

public class SupplyDiff extends BaseModel {
    /**
     *供需差异ID
     */
    private Integer id;
    /**
     *工厂
     */
    private Integer plant;
    /**
     *物料号
     */
    private String materialNumber;
    /**
     *物料描述
     */
    private String materialDescription;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 过期
     */
    private Integer pastDue;
    private Integer nextDay;
    private Integer thirdDay;
    private Integer fourDay;
    private Integer fiveDay;
    private Integer sixDay;
    private Integer seventhDay;
    private Integer eighthDay;
    private Integer ninthDay;
    private Integer tenDay;
    private Integer eleventhDay;
    private Integer twelfthDay;
    private Integer thirteenDay;
    private Integer fourteenthDay;

    //第十五天
    private Integer fifteenthDay;
    private Integer sixteenthDay;
    private Integer seventeenthDay;
    private Integer eighteenthDay;
    private Integer nineteenthDay;
    private Integer twentyDay;
    private Integer twentyOneDay;
    private Integer twentyTwoDay;
    private Integer twentyThreeDay;
    private Integer twentyFourDay;
    private Integer twentyFiveDay;
    private Integer twentySixDay;
    private Integer twentySeventhDay;
    private Integer twentyEighthDay;
    private Integer twentyNineDay;
    private Integer thirtyDay;
    private Integer thirtyOneDay;
    private Integer thirtyTwoDay;
    private Integer thirtyThreeDay;
    private Integer thirtyFourDay;
    private Integer thirtyFiveDay;
    private Integer thirtySixDay;
    private Integer thickSeventhDay;
    private Integer thickEighthDay;
    private Integer thickNineDay;
    private Integer fortyDay;
    private Integer fortyOneDay;
    private String supplierCode;
    private Integer materialUpTime;

    private Integer qty;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPastDue() {
        return pastDue;
    }

    public void setPastDue(Integer pastDue) {
        this.pastDue = pastDue;
    }

    public Integer getNextDay() {
        return nextDay;
    }

    public void setNextDay(Integer nextDay) {
        this.nextDay = nextDay;
    }

    public Integer getThirdDay() {
        return thirdDay;
    }

    public void setThirdDay(Integer thirdDay) {
        this.thirdDay = thirdDay;
    }

    public Integer getFourDay() {
        return fourDay;
    }

    public void setFourDay(Integer fourDay) {
        this.fourDay = fourDay;
    }

    public Integer getFiveDay() {
        return fiveDay;
    }

    public void setFiveDay(Integer fiveDay) {
        this.fiveDay = fiveDay;
    }

    public Integer getSixDay() {
        return sixDay;
    }

    public void setSixDay(Integer sixDay) {
        this.sixDay = sixDay;
    }

    public Integer getSeventhDay() {
        return seventhDay;
    }

    public void setSeventhDay(Integer seventhDay) {
        this.seventhDay = seventhDay;
    }

    public Integer getEighthDay() {
        return eighthDay;
    }

    public void setEighthDay(Integer eighthDay) {
        this.eighthDay = eighthDay;
    }

    public Integer getNinthDay() {
        return ninthDay;
    }

    public void setNinthDay(Integer ninthDay) {
        this.ninthDay = ninthDay;
    }

    public Integer getTenDay() {
        return tenDay;
    }

    public void setTenDay(Integer tenDay) {
        this.tenDay = tenDay;
    }

    public Integer getEleventhDay() {
        return eleventhDay;
    }

    public void setEleventhDay(Integer eleventhDay) {
        this.eleventhDay = eleventhDay;
    }

    public Integer getTwelfthDay() {
        return twelfthDay;
    }

    public void setTwelfthDay(Integer twelfthDay) {
        this.twelfthDay = twelfthDay;
    }

    public Integer getThirteenDay() {
        return thirteenDay;
    }

    public void setThirteenDay(Integer thirteenDay) {
        this.thirteenDay = thirteenDay;
    }

    public Integer getFourteenthDay() {
        return fourteenthDay;
    }

    public void setFourteenthDay(Integer fourteenthDay) {
        this.fourteenthDay = fourteenthDay;
    }

    public Integer getFifteenthDay() {
        return fifteenthDay;
    }

    public void setFifteenthDay(Integer fifteenthDay) {
        this.fifteenthDay = fifteenthDay;
    }

    public Integer getSixteenthDay() {
        return sixteenthDay;
    }

    public void setSixteenthDay(Integer sixteenthDay) {
        this.sixteenthDay = sixteenthDay;
    }

    public Integer getSeventeenthDay() {
        return seventeenthDay;
    }

    public void setSeventeenthDay(Integer seventeenthDay) {
        this.seventeenthDay = seventeenthDay;
    }

    public Integer getEighteenthDay() {
        return eighteenthDay;
    }

    public void setEighteenthDay(Integer eighteenthDay) {
        this.eighteenthDay = eighteenthDay;
    }

    public Integer getNineteenthDay() {
        return nineteenthDay;
    }

    public void setNineteenthDay(Integer nineteenthDay) {
        this.nineteenthDay = nineteenthDay;
    }

    public Integer getTwentyDay() {
        return twentyDay;
    }

    public void setTwentyDay(Integer twentyDay) {
        this.twentyDay = twentyDay;
    }

    public Integer getTwentyOneDay() {
        return twentyOneDay;
    }

    public void setTwentyOneDay(Integer twentyOneDay) {
        this.twentyOneDay = twentyOneDay;
    }

    public Integer getTwentyTwoDay() {
        return twentyTwoDay;
    }

    public void setTwentyTwoDay(Integer twentyTwoDay) {
        this.twentyTwoDay = twentyTwoDay;
    }

    public Integer getTwentyThreeDay() {
        return twentyThreeDay;
    }

    public void setTwentyThreeDay(Integer twentyThreeDay) {
        this.twentyThreeDay = twentyThreeDay;
    }

    public Integer getTwentyFourDay() {
        return twentyFourDay;
    }

    public void setTwentyFourDay(Integer twentyFourDay) {
        this.twentyFourDay = twentyFourDay;
    }

    public Integer getTwentyFiveDay() {
        return twentyFiveDay;
    }

    public void setTwentyFiveDay(Integer twentyFiveDay) {
        this.twentyFiveDay = twentyFiveDay;
    }

    public Integer getTwentySixDay() {
        return twentySixDay;
    }

    public void setTwentySixDay(Integer twentySixDay) {
        this.twentySixDay = twentySixDay;
    }

    public Integer getTwentySeventhDay() {
        return twentySeventhDay;
    }

    public void setTwentySeventhDay(Integer twentySeventhDay) {
        this.twentySeventhDay = twentySeventhDay;
    }

    public Integer getTwentyEighthDay() {
        return twentyEighthDay;
    }

    public void setTwentyEighthDay(Integer twentyEighthDay) {
        this.twentyEighthDay = twentyEighthDay;
    }

    public Integer getTwentyNineDay() {
        return twentyNineDay;
    }

    public void setTwentyNineDay(Integer twentyNineDay) {
        this.twentyNineDay = twentyNineDay;
    }

    public Integer getThirtyDay() {
        return thirtyDay;
    }

    public void setThirtyDay(Integer thirtyDay) {
        this.thirtyDay = thirtyDay;
    }

    public Integer getThirtyOneDay() {
        return thirtyOneDay;
    }

    public void setThirtyOneDay(Integer thirtyOneDay) {
        this.thirtyOneDay = thirtyOneDay;
    }

    public Integer getThirtyTwoDay() {
        return thirtyTwoDay;
    }

    public void setThirtyTwoDay(Integer thirtyTwoDay) {
        this.thirtyTwoDay = thirtyTwoDay;
    }

    public Integer getThirtyThreeDay() {
        return thirtyThreeDay;
    }

    public void setThirtyThreeDay(Integer thirtyThreeDay) {
        this.thirtyThreeDay = thirtyThreeDay;
    }

    public Integer getThirtyFourDay() {
        return thirtyFourDay;
    }

    public void setThirtyFourDay(Integer thirtyFourDay) {
        this.thirtyFourDay = thirtyFourDay;
    }

    public Integer getThirtyFiveDay() {
        return thirtyFiveDay;
    }

    public void setThirtyFiveDay(Integer thirtyFiveDay) {
        this.thirtyFiveDay = thirtyFiveDay;
    }

    public Integer getThirtySixDay() {
        return thirtySixDay;
    }

    public void setThirtySixDay(Integer thirtySixDay) {
        this.thirtySixDay = thirtySixDay;
    }

    public Integer getThickSeventhDay() {
        return thickSeventhDay;
    }

    public void setThickSeventhDay(Integer thickSeventhDay) {
        this.thickSeventhDay = thickSeventhDay;
    }

    public Integer getThickEighthDay() {
        return thickEighthDay;
    }

    public void setThickEighthDay(Integer thickEighthDay) {
        this.thickEighthDay = thickEighthDay;
    }

    public Integer getThickNineDay() {
        return thickNineDay;
    }

    public void setThickNineDay(Integer thickNineDay) {
        this.thickNineDay = thickNineDay;
    }

    public Integer getFortyDay() {
        return fortyDay;
    }

    public void setFortyDay(Integer fortyDay) {
        this.fortyDay = fortyDay;
    }

    public Integer getFortyOneDay() {
        return fortyOneDay;
    }

    public void setFortyOneDay(Integer fortyOneDay) {
        this.fortyOneDay = fortyOneDay;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getMaterialUpTime() {
        return materialUpTime;
    }

    public void setMaterialUpTime(Integer materialUpTime) {
        this.materialUpTime = materialUpTime;
    }
}
