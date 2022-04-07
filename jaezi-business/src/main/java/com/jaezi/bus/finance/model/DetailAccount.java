package com.jaezi.bus.finance.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 17:00
 * @description
 * 明细账
 */
public class DetailAccount  extends BaseModel {

    /**
     * id
     */
    @ExcelIgnore
    private Integer id;

    /**
     * 公司
     */
    @ExcelProperty(value = "公司")
    private String company;

    /**
     * 日期
     */
    @ExcelProperty(value = "日期")
    private String date;

    /**
     * 凭证号
     */
    @ExcelProperty(value = "凭证号")
    private Integer voucherNumber;

    /**
     * 摘要
     */
    @ExcelProperty(value = "摘要")
    private String digest;

    /**
     * （借方）本币
     */
    @ExcelProperty(value = "(借方)本币")
    private Integer debitDomestic;

    /**
     * （贷方）本币
     */
    @ExcelProperty(value = "(贷方)本币")
    private Integer creditDomestic;

    /**
     * 方向
     */
    @ExcelProperty(value = "方向")
    private String direction;

    /**
     * （余额）本币
     */
    @ExcelProperty(value = "(余额)本币")
    private Integer balanceDomestic;

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(Integer voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Integer getDebitDomestic() {
        return debitDomestic;
    }

    public void setDebitDomestic(Integer debitDomestic) {
        this.debitDomestic = debitDomestic;
    }

    public Integer getCreditDomestic() {
        return creditDomestic;
    }

    public void setCreditDomestic(Integer creditDomestic) {
        this.creditDomestic = creditDomestic;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getBalanceDomestic() {
        return balanceDomestic;
    }

    public void setBalanceDomestic(Integer balanceDomestic) {
        this.balanceDomestic = balanceDomestic;
    }
}
