package com.jaezi.bus.purchase.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.bus.purchase.dao.ProductionPlanImportDao;
import com.jaezi.bus.purchase.model.ProductionPlanImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel生产计划监听类
 */

public class ProductionPlanCheckImportListener extends AnalysisEventListener<ProductionPlanImport> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductionPlanCheckImportListener.class);

    List<ProductionPlanImport> list = new LinkedList<>();

    @Override
    public void invoke(ProductionPlanImport productionPlanImport, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(productionPlanImport));
        if (JSON.toJSONString(productionPlanImport).length() > 2) {
            list.add(productionPlanImport);
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
    public List<ProductionPlanImport> getData() {
        LOGGER.info("{}条数据，开始验证数据！", list.size());
        return list;
    }
}
