package com.jaezi.bus.purchase.service;

import com.jaezi.bus.purchase.dao.SupplierBasicDataDao;
import com.jaezi.bus.purchase.model.SupplierBasicData;
import com.jaezi.bus.purchase.vo.SupplierBasicDataVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 供应商基础数据逻辑层
 */

@Service
public class SupplierBasicDataService extends BaseService<SupplierBasicData, SupplierBasicDataVo> {

    private SupplierBasicDataDao supplierBasicDataDao;

    @Autowired
    public void setBaseDao(SupplierBasicDataDao supplierBasicDataDao) {
        super.setBaseDao(supplierBasicDataDao);
        this.supplierBasicDataDao = supplierBasicDataDao;
    }

}
