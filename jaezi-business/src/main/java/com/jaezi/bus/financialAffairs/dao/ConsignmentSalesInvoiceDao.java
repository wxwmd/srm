package com.jaezi.bus.financialAffairs.dao;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoice;
import com.jaezi.bus.financialAffairs.vo.ConsignmentSalesInvoiceVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/11  9:54:51
 * @description 寄售物资持久层
 */
@Repository
public interface ConsignmentSalesInvoiceDao extends BaseDao<ConsignmentSalesInvoice, ConsignmentSalesInvoiceVo> {

    /**
     * 查询所有寄售物资
     *
     * @param filter plant 工厂
     *               invoiceDate 发票日期
     *               invoiceNumber 发票号
     *               invoiceStatus 发票状态
     * @return ConsignmentSalesInvoice>
     */
    List<ConsignmentSalesInvoice> findAll(Map<String, String> filter);
}
