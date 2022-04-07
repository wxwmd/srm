package com.jaezi.bus.financialAffairs.dao;

import com.jaezi.bus.financialAffairs.model.StandardInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.StandardInvoiceOutInfoVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/27  16:15:47
 * @description
 */
@Repository
public interface StandardInvoiceOutInfoDao extends BaseDao<StandardInvoiceOutInfo, StandardInvoiceOutInfoVo> {

    /**
     * 查询所有标准物资开票
     *
     * @param filter plant 工厂
     *               startTime 开始时间
     *               entTime 结束时间
     * @return StandardInvoiceOut>
     */
    List<StandardInvoiceOutInfo> findAll(Map<String, String> filter);

    /**
     * 根据临时订单号删除 标准物资开票
     *
     * @param interimInvoiceNumber 临时订单号
     * @return int
     */
    int deleteByInterimInvoiceNumber(Integer interimInvoiceNumber);

    /**
     * 根据临时发票号查询
     *
     * @param interimInvoiceNumber 临时发票号
     * @return StandardInvoiceOutInfo>
     */
    List<StandardInvoiceOutInfo> findByInterimInvoiceNumber(Integer interimInvoiceNumber);
}
