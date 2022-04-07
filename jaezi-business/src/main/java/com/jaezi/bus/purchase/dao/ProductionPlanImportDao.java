package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.ProductionPlanImport;
import com.jaezi.bus.purchase.vo.ProductionPlanImportVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 生产计划导入持久层
 */

@Repository
public interface ProductionPlanImportDao extends BaseDao<ProductionPlanImport, ProductionPlanImportVo> {
    /**
     * 批量更新生产计划导入
     *
     * @param list 生产计划导入集合
     * @return int
     */
    int updateBath(List<ProductionPlanImport> list);

    /**
     * 批量插入生产计划导入
     *
     * @param list 生产计划导入集合
     * @return int
     */
    int saveBath(List<ProductionPlanImport> list);

    /**
     * 验证数据库是否有数据
     *
     * @param materialNumberList 物料号集合
     * @param supplierCodeList   供应商集合
     * @return String
     */
    List<String> getProductionPlanBy(List<String> materialNumberList, List<String> supplierCodeList);
}
