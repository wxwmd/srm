package com.jaezi.bus.finance.service;

import com.jaezi.bus.finance.dao.RateRedInvoiceDao;
import com.jaezi.bus.finance.model.RateRedInvoice;
import com.jaezi.bus.finance.vo.RateRedInvoiceVo;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceDao;
import com.jaezi.bus.financialAffairs.model.StandardInvoice;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/25 19:00
 * @description 返利红字发票业务层
 */
@Service
public class RateRedInvoiceService extends BaseService<RateRedInvoice, RateRedInvoiceVo> {

    private RateRedInvoiceDao rateRedInvoiceDao;

    private StandardInvoiceDao standardInvoiceDao;

    @Autowired
    public void setBaseDao(RateRedInvoiceDao rateRedInvoiceDao, StandardInvoiceDao standardInvoiceDao) {
        super.setBaseDao(rateRedInvoiceDao);
        this.rateRedInvoiceDao = rateRedInvoiceDao;
        this.standardInvoiceDao = standardInvoiceDao;
    }

    /**
     * 将填写的返利红字发票信息，添加到标准物质发票中，并设置状态为已提交
     *
     * @param rateRedInvoice 返利红字发票信息
     * @param username       用户名
     * @return int
     * @author whj
     * @date 2021/8/26
     * @since 1.0
     */
    public int addRateRedInvoice(RateRedInvoice rateRedInvoice, String username) {
        int result = 0;
        if (rateRedInvoice.getDiscountReason() != null) {
            StandardInvoice standardInvoice = new StandardInvoice();
            standardInvoice.setInvoiceNumber(rateRedInvoice.getInvoiceNumber());
            standardInvoice.setInvoiceDate(rateRedInvoice.getInvoiceDate());
            standardInvoice.setAmount(rateRedInvoice.getTaxExclusivePrice());
            standardInvoice.setInvoiceCode(rateRedInvoice.getInvoiceCode());
            standardInvoice.setAggregateAmount(rateRedInvoice.getTaxExclusivePrice());
            standardInvoice.setTaxPriceTotal(rateRedInvoice.getTaxPriceTotal());
            standardInvoice.setTaxRate(rateRedInvoice.getTaxRate());
            standardInvoice.setTaxPrice(rateRedInvoice.getTaxAmount());
            standardInvoice.setInvoiceStatus(0);
            standardInvoice.setInvoiceType(1);
            standardInvoice.setSupplierCode(username);
            standardInvoice.setAuditStatus(0);
            standardInvoice.setPlant(rateRedInvoice.getPlant());
            standardInvoiceDao.add(standardInvoice);
            rateRedInvoice.setSupplierCode(username);
            return rateRedInvoiceDao.add(rateRedInvoice);
        }
        return result;
    }
}
