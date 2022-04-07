package com.jaezi.system.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.jaezi.common.util.SHA256;
import com.jaezi.system.dao.SupplierDao;
import com.jaezi.system.dao.UserDao;
import com.jaezi.system.dto.SupplierDto;
import com.jaezi.system.model.Supplier;
import com.jaezi.system.model.User;
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
 * @description excel生产计划监听类
 */

public class SupplierImportListener extends AnalysisEventListener<SupplierDto> {

    private final UserDao userDao;
    private final SupplierDao supplierDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierImportListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<SupplierDto> list = new Vector<>();

    public SupplierImportListener(UserDao userDao, SupplierDao supplierDao) {
        this.userDao = userDao;
        this.supplierDao = supplierDao;
    }

    @Override
    public void invoke(SupplierDto supplierDto, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(supplierDto));
        list.add(supplierDto);
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
     * 更新供应商信息
     */
    private synchronized void updateData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        List<User> userList = new LinkedList<>();
        for (SupplierDto supplierDto : list) {
            if (null == userDao.getUserByUsernameAndStatus(supplierDto.getUsername(), null)) {
                User user = new User();
                user.setUsername(supplierDto.getUsername());
                user.setPassword(SHA256.hash(supplierDto.getUsername()));
                user.setRealName(supplierDto.getUsername());
                user.setRoleId(3);
                user.setStatus(0);
                user.setUserType(1);
                //添加用户写入创建时间时间戳
                user.setCreateTime(System.currentTimeMillis());
                userList.add(user);
            }
        }
        if (!ObjectUtils.isEmpty(userList)) {
            int rest = userDao.saveBath(userList);

            if (rest > 0) {
                List<Supplier> supplierList = new LinkedList<>();
                for (SupplierDto supplierDto : list) {
                    for (User user : userList) {
                        if (user.getUsername().equals(supplierDto.getUsername()) && !ObjectUtils.isEmpty(user.getId())) {
                            Supplier supplier = new Supplier();
                            supplier.setExamineStatus(1);
                            supplier.setCompanyName(supplierDto.getCompanyName());
                            supplier.setUserId(user.getId());
                            supplierList.add(supplier);
                            break;
                        }
                    }
                }
                if (!ObjectUtils.isEmpty(supplierList)) {
                    supplierDao.bathAdd(supplierList);
                }
            }
        }

        LOGGER.info("存储数据库成功！");
    }
}
