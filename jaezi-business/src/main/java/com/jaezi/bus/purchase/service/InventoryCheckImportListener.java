package com.jaezi.bus.purchase.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.bus.purchase.dao.InventoryImportDao;
import com.jaezi.bus.purchase.model.InventoryImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel库存导入监听类
 */

public class InventoryCheckImportListener extends AnalysisEventListener<InventoryImport> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryCheckImportListener.class);

    List<InventoryImport> list = new LinkedList<>();

    @Override
    public void invoke(InventoryImport inventoryImport, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(inventoryImport));
        if (JSON.toJSONString(inventoryImport).length() > 2) {
            list.add(inventoryImport);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    /**
     * 批量更新
     */
    public List<InventoryImport> getData() {
        LOGGER.info("{}条数据，开始验证数据！", list.size());
        return list;
    }
}
