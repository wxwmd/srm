package com.jaezi.bus.financialAffairs.dao;

import com.jaezi.bus.financialAffairs.model.StandardInvoiceOut;
import com.jaezi.bus.financialAffairs.vo.StandardInvoiceOutVo;
import com.jaezi.bus.purchase.model.Purchase;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9  20:42:46
 * @description
 */
@Repository
public interface StandardInvoiceOutDao extends BaseDao<StandardInvoiceOut, StandardInvoiceOutVo> {

    /**
     * 查询所有标准物资开票
     *
     * @param filter plant 工厂
     *               startTime 开始时间
     *               entTime 结束时间
     * @return StandardInvoiceOut>
     */
    List<StandardInvoiceOut> findAll(Map<String, String> filter);

    /**
     * 查询所有标准物资开票v1
     *
     * @param filter plant 工厂
     *               startTime 开始时间
     *               entTime 结束时间
     * @return StandardInvoiceOut>
     */
    List<StandardInvoiceOut> findAll1(Map<String, String> filter);

    /**
     * 根据采购订单查询标准物资开票对象
     *
     * @param purchaseOrder 标准物资开票对象
     * @param material      物料
     * @return OutInvoice 对象
     * @author yx
     * @date 2021年8月17日17:08:52
     * @since 1.0
     */
    StandardInvoiceOut getStandardByPOrderAndMat(String purchaseOrder, String material);

    /**
     * 批量插入标准物资开票
     *
     * @param list 标准物资信息集合
     * @return
     */
    int saveBath(List<StandardInvoiceOut> list);

    /**
     * 获取最大序号
     *
     * @return
     */
    int getMaxId();
}
