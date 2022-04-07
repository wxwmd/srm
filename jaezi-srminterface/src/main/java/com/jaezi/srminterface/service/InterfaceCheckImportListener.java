package com.jaezi.srminterface.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.common.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel表数据导入监听类
 */
public class InterfaceCheckImportListener extends AnalysisEventListener<Map<Integer, String>> {

    private final InterfaceCommService interfaceCommService;
    private final String tableName;

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceCheckImportListener.class);

    List<Map<Integer, String>> list = new LinkedList<>();

    public InterfaceCheckImportListener(InterfaceCommService interfaceCommService, String tableName) {
        this.interfaceCommService = interfaceCommService;
        this.tableName = tableName;
    }

    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(map));
        list.add(map);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        updateData();
//        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 批量更新
     */
    public void updateData() throws BaseException {
        if (ObjectUtils.isEmpty(list)) {
            throw new BaseException("配置表未配置,请检查");
        }
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

        interfaceCommService.tableCheckService(tableName, dataMapList);

        LOGGER.info("存储数据库成功！");
    }

}
