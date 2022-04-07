package com.jaezi.bus.financialAffairs.dto;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOut;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/30  18:56:53
 * @description
 */
public class ConsignmentSalesInvoiceOutDto {

    private List<ConsignmentSalesInvoiceOut> consignmentSalesInvoiceOuts;

    private List<BigDecimal> resultList;

    public List<ConsignmentSalesInvoiceOut> getConsignmentSalesInvoiceOuts() {
        return consignmentSalesInvoiceOuts;
    }

    public void setConsignmentSalesInvoiceOuts(List<ConsignmentSalesInvoiceOut> consignmentSalesInvoiceOuts) {
        this.consignmentSalesInvoiceOuts = consignmentSalesInvoiceOuts;
    }

    public List<BigDecimal> getResultList() {
        return resultList;
    }

    public void setResultList(List<BigDecimal> resultList) {
        this.resultList = resultList;
    }
}
