package com.jaezi.srminterface.dao;

import com.jaezi.srminterface.model.MaterialSupplier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 物料/供应商中间表持久层
 */
@Repository
public interface MaterialSupplierDao {

    /**
     * 批量插入物料/供应商中间表
     *
     * @param list 物料/供应商中间集合
     * @return int
     */
    int saveBath(List<MaterialSupplier> list);

    /**
     * 通过物料号获取供应商编号
     *
     * @param materialSupplyDemand 物料号
     * @return String
     */
    String getSupplierByMaterial(String materialSupplyDemand);
}
