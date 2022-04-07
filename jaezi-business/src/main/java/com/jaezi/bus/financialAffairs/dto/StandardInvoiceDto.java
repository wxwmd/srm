package com.jaezi.bus.financialAffairs.dto;

import com.jaezi.bus.financialAffairs.model.StandardInvoice;

import java.util.List;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/26  18:09:56
 * @description
 */
public class StandardInvoiceDto {

    /**
     * 标准物资发票集合
     */
    private List<StandardInvoice> standardInvoices;

    /**
     * 总金额
     */
    private String aggregateAmount;

    public List<StandardInvoice> getStandardInvoices() {
        return standardInvoices;
    }

    public void setStandardInvoices(List<StandardInvoice> standardInvoices) {
        this.standardInvoices = standardInvoices;
    }

    public String getAggregateAmount() {
        return aggregateAmount;
    }

    public void setAggregateAmount(String aggregateAmount) {
        this.aggregateAmount = aggregateAmount;
    }
}
