package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.Purchase;
import com.jaezi.bus.purchase.vo.PurchaseVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 采购订单数据处理
 */

@Repository
public interface PurchaseDao extends BaseDao<Purchase, PurchaseVo> {

    /**
     * 根据采购订单号查询采购订单列表
     *
     * @param pOrder         采购订单号
     * @param materialNumber 物料号
     * @return
     */
    Purchase getByPurOrderAndMatNum(String pOrder, String materialNumber);

    /**
     * 批量插入采购订单
     *
     * @param purchaseList 订单集合
     * @return
     */
    int save(List<Purchase> purchaseList);

    /**
     * 订单未确认条数
     *
     * @param filter userType 用户类型
     *               realName 昵称
     * @return int
     */
    int unconfirmed(Map<String, String> filter);
}