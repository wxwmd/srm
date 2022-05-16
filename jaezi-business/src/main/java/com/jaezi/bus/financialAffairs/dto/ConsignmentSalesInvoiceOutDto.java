package com.jaezi.bus.financialAffairs.dto;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOut;
import com.jaezi.common.bean.DataGrid;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wxw
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2022/5/16
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
