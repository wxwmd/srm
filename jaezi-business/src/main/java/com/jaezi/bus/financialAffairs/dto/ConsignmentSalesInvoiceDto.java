package com.jaezi.bus.financialAffairs.dto;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/26  19:18:53
 * @description
 */
public class ConsignmentSalesInvoiceDto extends ConsignmentSalesInvoice{

    private Map<String, BigDecimal> resultList;

    private List<Map<String,String>> combinedData;

    public Map<String, BigDecimal> getResultList() {
        return resultList;
    }

    public void setResultList(Map<String, BigDecimal> resultList) {
        this.resultList = resultList;
    }

    public List<Map<String, String>> getCombinedData() {
        return combinedData;
    }

    public void setCombinedData(List<Map<String, String>> combinedData) {
        this.combinedData = combinedData;
    }
}
