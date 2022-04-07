package com.jaezi.bus.inventory.dao;

import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTRParticular;
import com.jaezi.bus.inventory.vo.ConsignmentGoodsSDTRParticularVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/11/15  14:59:54
 * @description
 */
@Repository
public interface ConsignmentGoodsSDTRParticularDao extends BaseDao<ConsignmentGoodsSDTRParticular, ConsignmentGoodsSDTRParticularVo> {

    /**
     * 查询所有寄售物资结收发存详情
     *
     * @param filter line 行号
     *               materialNumber 物料号
     * @return ConsignmentGoodsSDTRParticular>
     */
    List<ConsignmentGoodsSDTRParticular> findAll(Map<String, String> filter);
}
