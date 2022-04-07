package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.Inventory;
import com.jaezi.bus.purchase.vo.InventoryVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 供应商库存数据访问对象
 */

@Repository
public interface InventoryDao extends BaseDao<Inventory, InventoryVo> {

    /**
     * 批量插入供应商库存
     *
     * @param inventoryList 供应商库存集合
     * @return int
     */
    int save(List<Inventory> inventoryList);

    /**
     * 更新库存匹配
     *
     * @param
     * @return void
     */
    int updateInventory();

    /**
     * 删除全表库存匹配数据
     *
     * @param
     * @return int
     */
    int truncateInventory();

    /**
     * 供应商库存按 交货日期 排序
     *
     * @param
     * @return Inventory>
     */
    List<Inventory> getInventoryOrderBy();
}
