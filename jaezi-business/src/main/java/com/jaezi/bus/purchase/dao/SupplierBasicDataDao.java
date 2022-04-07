package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.SupplierBasicData;
import com.jaezi.bus.purchase.vo.SupplierBasicDataVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 供应商基础数据数据处理
 */

@Repository
public interface SupplierBasicDataDao extends BaseDao<SupplierBasicData, SupplierBasicDataVo> {
    /**
     * 批量插入供应商基础数据
     *
     * @param supplierBasicDataList 供应商基础数据集合
     * @return int
     */
    int save(List<SupplierBasicData> supplierBasicDataList);
}
