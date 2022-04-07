package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.SupplierChange;
import com.jaezi.synergia.vo.SupplierChangeVo;
import org.springframework.stereotype.Repository;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 供应商技术、厂址变更数据访问层
 */
@Repository
public interface SupplierChangeDao extends BaseDao<SupplierChange, SupplierChangeVo> {

}
