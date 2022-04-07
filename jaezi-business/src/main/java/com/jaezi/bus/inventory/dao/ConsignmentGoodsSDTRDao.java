package com.jaezi.bus.inventory.dao;

import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
import com.jaezi.bus.inventory.vo.ConsignmentGoodsSDTRVo;
import com.jaezi.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9 15:03
 * @description 寄售物资结收发存数据层
 */
@Repository
public interface ConsignmentGoodsSDTRDao extends BaseDao<ConsignmentGoodsSDTR, ConsignmentGoodsSDTRVo> {
    /**
     * 查询所有寄售物资结收发存
     *
     * @param filter supplierCode 供应商
     *               materialName 物料名称
     *               firstTerm 期初
     *               income 收入
     *               emit 发出
     *               lastTerm 期末
     *               afterTax 不含税金额
     *               outstanding 未结算
     *               clsd 已结算
     *               line 行号
     *               materialNumber 物料号
     *               factory 工厂
     *               startTime 开始时间
     *               entTime 结束时间
     *               userType 结束时间
     * @return ConsignmentGoodsSDTR>
     */
    List<ConsignmentGoodsSDTR> findAll(Map<String, String> filter);
}
