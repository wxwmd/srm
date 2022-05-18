package com.jaezi.bus.financialAffairs.dao;

import com.jaezi.bus.financialAffairs.model.StandardInvoice;
import com.jaezi.bus.financialAffairs.vo.StandardInvoiceVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10  9:01:42
 * @description
 */
@Repository
public interface StandardInvoiceDao extends BaseDao<StandardInvoice, StandardInvoiceVo> {

    /**
     * 查询所有标准物资开票
     *
     * @param filter id 发票id
     *               serialNumber 序号
     *               invoiceType 发票类型
     *               interimInvoiceNumber 临时发票号
     *               invoiceNumber 发票号
     *               startTime 开始时间
     *               endTime 结束时间
     *               invoiceStatus 发票状态
     *               invoiceDate 发票日期
     * @return StandardInvoice>
     */
    List<StandardInvoice> findAll(Map<String, String> filter);
}
