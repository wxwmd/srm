package com.jaezi.bus.inventory.dao;

import com.jaezi.bus.inventory.model.OutsourcingSubcontractInventory;
import com.jaezi.bus.inventory.vo.OutsourcingSubcontractInventoryVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9 15:05
 * @description 外协分包库存数据层
 */
@Repository
public interface OutsourcingSubcontractInventoryDao extends BaseDao<OutsourcingSubcontractInventory, OutsourcingSubcontractInventoryVo> {

    /**
     * 查询所有外协分包库存
     *
     * @param filter userType 用户类型
     *               materialNumber 物料号
     *               materialDescription 物料描述
     *               plant 工厂
     *               qty 库存数量
     *               qty1 宇通库存
     * @return OutsourcingSubcontractInventory>
     */
    List<OutsourcingSubcontractInventory> findAll(Map<String, String> filter);
}
