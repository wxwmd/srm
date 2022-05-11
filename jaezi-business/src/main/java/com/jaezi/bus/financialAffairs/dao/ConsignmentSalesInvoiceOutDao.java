package com.jaezi.bus.financialAffairs.dao;

import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOut;
import com.jaezi.bus.financialAffairs.vo.ConsignmentSalesInvoiceOutVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10  16:49:05
 * @description 寄售物资发票持久层
 */
@Repository
public interface ConsignmentSalesInvoiceOutDao extends BaseDao<ConsignmentSalesInvoiceOut, ConsignmentSalesInvoiceOutVo> {

    /**
     * 查询所有寄售物资发票
     *
     * @param filter plant 工厂
     *               status 状态
     *               startTime 开始时间
     *               endTime 结束时间
     *               invoiceGroup 发票组
     * @return ConsignmentSalesInvoiceOut>
     */
    List<ConsignmentSalesInvoiceOut> findAll(Map<String, String> filter);

    /**
     * 根据采购订单查询寄售物资对象
     *
     * @param purchaseOrder 采购订单
     * @param materialNumber 物料号
     * @return ConsignmentSalesInvoice 对象
     * @author yx
     * @date 2021年8月18日10:35:16
     * @since 1.0
     */
    ConsignmentSalesInvoiceOut getConsignmentByPOrderAndMatNum(String purchaseOrder, String materialNumber);

    ConsignmentSalesInvoiceOut getConssignmentByInvoiceNumAndMatNum(String invoiceNumber,String materialNumber);
}
