package com.jaezi.bus.financialAffairs.dao;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.ConsignmentSalesInvoiceOutInfoVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/31  9:23:39
 * @description 寄售物资发票
 */
@Repository
public interface ConsignmentSalesInvoiceOutInfoDao extends BaseDao<ConsignmentSalesInvoiceOutInfo, ConsignmentSalesInvoiceOutInfoVo> {

    /**
     * 查询所有寄售物资发票
     *
     * @param filter plant 工厂
     *               invoiceNumber 发票号
     * @return ConsignmentSalesInvoiceOutInfo>
     */
    List<ConsignmentSalesInvoiceOutInfo> findAll(Map<String, String> filter);

    int updateInvoiceNumber(Integer oldInvoiceNumber,Integer newInvoiceNumber);
}
