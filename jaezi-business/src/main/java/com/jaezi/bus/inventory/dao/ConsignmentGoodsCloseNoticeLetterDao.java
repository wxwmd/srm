package com.jaezi.bus.inventory.dao;

import com.jaezi.bus.inventory.model.ConsignmentGoodsCloseNoticeLetter;
import com.jaezi.bus.inventory.vo.ConsignmentGoodsCloseNoticeLetterVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10 17:31
 * @description 寄售物资结算通知单数据层
 */
@Repository
public interface ConsignmentGoodsCloseNoticeLetterDao extends BaseDao<ConsignmentGoodsCloseNoticeLetter, ConsignmentGoodsCloseNoticeLetterVo> {

    /**
     * 查询所有寄售物资结算通知单
     *
     * @param filter factory 工厂
     *               supplier 供应商
     *               period 期间
     *               specialSuggestion 特别提示
     *               number 序号
     *               materialNumber 物料号
     *               materialName 物料名称
     *               count 数量
     *               startTime 开始时间
     *               entTime 结束时间
     *               userType 用户类型
     * @return ConsignmentGoodsCloseNoticeLetter>
     */
    List<ConsignmentGoodsCloseNoticeLetter> findAll(Map<String, String> filter);
}
