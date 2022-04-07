package com.jaezi.bus.purchase.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.bus.purchase.dao.PurchaseDao;
import com.jaezi.bus.purchase.dto.PurchaseDto;
import com.jaezi.bus.purchase.model.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Vector;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel导入订单监听类
 */

public class PurchaseImportListener extends AnalysisEventListener<PurchaseDto> {

    private final PurchaseDao purchaseDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseImportListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<Purchase> list = new Vector<>();

    public PurchaseImportListener(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @Override
    public void invoke(PurchaseDto purchaseDto, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(purchaseDto));
        Purchase purchase = new Purchase();
        //属性复制
        BeanUtils.copyProperties(purchaseDto, purchase);
        list.add(purchase);
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
        purchaseDao.save(list);

        LOGGER.info("存储数据库成功！");
    }
}
