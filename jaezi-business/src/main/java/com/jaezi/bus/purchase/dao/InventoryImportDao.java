package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.InventoryImport;
import com.jaezi.bus.purchase.vo.InventoryImportVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 供应商库存导入持久层
 */

@Repository
public interface InventoryImportDao extends BaseDao<InventoryImport, InventoryImportVo> {

    /**
     * 批量更新供应商库存导入
     *
     * @param list 供应商库存导入数据
     * @return int
     */
    int updateBath(List<InventoryImport> list);

    /**
     * 批量插入供应商库存导入
     *
     * @param list 供应商库存导入数据
     * @return int
     */
    int saveBath(List<InventoryImport> list);

    /**
     * 验证数据库是否有数据
     *
     * @param materialNumberList 物料号集合
     * @param supplierCodeList   供应商集合
     * @return String>
     */
    List<String> getInventoryByMatNumsAndSuppCodes(List<String> materialNumberList, List<String> supplierCodeList);
}
