package com.jaezi.system.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.common.util.SHA256;
import com.jaezi.system.dao.MaterialDao;
import com.jaezi.system.dao.UserDao;
import com.jaezi.system.model.Material;
import com.jaezi.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description excel生产计划监听类
 */

public class MaterialImportListener extends AnalysisEventListener<Material> {

    private final MaterialDao materialDao;
    private final UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterialImportListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<Material> list = new Vector<>();

    public MaterialImportListener(MaterialDao materialDao, UserDao userDao) {
        this.materialDao = materialDao;
        this.userDao = userDao;
    }

    @Override
    public void invoke(Material material, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(material));
        list.add(material);
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
        materialDao.bathSave(list);

        List<String> buyerNumberList = list.stream().map(material -> material.getBuyerNumber()).distinct().collect(Collectors.toList());

        List<User> userList = new LinkedList<>();
        for (String buyerNumber : buyerNumberList) {
            if (null == userDao.getUserByUsernameAndStatus(buyerNumber, null)) {
                User user = new User();
                user.setUsername(buyerNumber);
                user.setPassword(SHA256.hash(buyerNumber));
                user.setRealName(buyerNumber);
                user.setRoleId(5);
                user.setStatus(0);
                user.setUserType(0);
                //添加用户写入创建时间时间戳
                user.setCreateTime(System.currentTimeMillis());
                userList.add(user);
            }
        }
        if (!ObjectUtils.isEmpty(userList)) {
            userDao.saveBath(userList);
        }

        LOGGER.info("存储数据库成功！");
    }
}
