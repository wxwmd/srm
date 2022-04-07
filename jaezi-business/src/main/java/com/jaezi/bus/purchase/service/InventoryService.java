package com.jaezi.bus.purchase.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.bus.purchase.dao.InventoryDao;
import com.jaezi.bus.purchase.model.Inventory;
import com.jaezi.bus.purchase.vo.InventoryVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.manager.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 库存数据逻辑处理
 */

@Service
public class InventoryService extends BaseService<Inventory, InventoryVo> {

    private InventoryDao inventoryDao;

    @Autowired
    public void setBaseDao(InventoryDao inventoryDao) {
        super.setBaseDao(inventoryDao);
        this.inventoryDao = inventoryDao;
    }
}
