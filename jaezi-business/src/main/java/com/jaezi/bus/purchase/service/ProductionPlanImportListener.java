package com.jaezi.bus.purchase.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.bus.purchase.dao.ProductionPlanImportDao;
import com.jaezi.bus.purchase.model.ProductionPlanImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Vector;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel生产计划监听类
 */

public class ProductionPlanImportListener extends AnalysisEventListener<ProductionPlanImport> {

    private final ProductionPlanImportDao productionPlanImportDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductionPlanImportListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<ProductionPlanImport> list = new Vector<>();

    public ProductionPlanImportListener(ProductionPlanImportDao productionPlanImportDao) {
        this.productionPlanImportDao = productionPlanImportDao;
    }

    @Override
    public void invoke(ProductionPlanImport productionPlanImport, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(productionPlanImport));
        list.add(productionPlanImport);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            updateData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        updateData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 批量更新
     */
    private synchronized void updateData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        productionPlanImportDao.updateBath(list);
        LOGGER.info("存储数据库成功！");
    }
}
