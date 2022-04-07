package com.jaezi.srminterface.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel表数据导入监听类
 */

public class InterfaceImportListener extends AnalysisEventListener<Map<Integer, String>> {

    private final String tableName;
    private final InterfaceCommService interfaceCommService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceImportListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<Map<Integer, String>> list = new Vector<>();
    Integer size = 0;

    public InterfaceImportListener(InterfaceCommService interfaceCommService, String tableName) {
        this.interfaceCommService = interfaceCommService;
        this.tableName = tableName;
    }

    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        if (list.size() > 0) {
            size = list.get(0).size();
            //缺失单元格的内容用null代替
            if (map.size() < size) {
                for (int i = map.size(); i < size; i++) {
                    map.put(i, null);
                }
            }
        }
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(map));
        list.add(map);
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

        Map<Integer, String> fileMap = list.get(0);
        list.remove(0);

        List<Map<String, Object>> dataMapList = new Vector();
        for (Map<Integer, String> map1 : list) {
            Map<String, Object> dataMap = new HashMap<>();
            for (Map.Entry<Integer, String> m : map1.entrySet()) {
                dataMap.put(fileMap.get(m.getKey()), m.getValue());
            }
            dataMapList.add(dataMap);
        }

        LOGGER.info("解析数据:{}", JSON.toJSONString(dataMapList));

        interfaceCommService.tableService(tableName, dataMapList);

        LOGGER.info("存储数据库成功！");
    }

}
