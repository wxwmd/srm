package com.jaezi.srminterface.timed;

import com.jaezi.common.config.ScheduledConfig;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.srminterface.dao.*;
import com.jaezi.srminterface.service.InterfaceCommService;
import org.springframework.context.annotation.Configuration;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 定时获取中间件信息导入系统表
 */

@Configuration
public class InterfaceCommServiceTask extends ScheduledConfig {

    private final InterfaceCommService interfaceCommService;

    public InterfaceCommServiceTask(InterfaceServerDao interfaceServerDao, TableConfigDao tableConfigDao, SqlConfigDao sqlConfigDao, MaterialSupplierDao materialSupplierDao,
                                    FiledConfigDao filedConfigDao) {
        interfaceCommService = new InterfaceCommService(interfaceServerDao, tableConfigDao, sqlConfigDao, materialSupplierDao, filedConfigDao);
    }

    @Override
    protected void processTask() {
        ThreadManager.getInstance().syncExecute(() -> {
            interfaceCommService.updateData();
        });
    }

    @Override
    protected String getCron() {
        return interfaceCommService.getCron();
    }
}
