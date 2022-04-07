package com.jaezi.bus.plan.timed;

import com.jaezi.bus.plan.service.SupplyDiffService;
import com.jaezi.common.config.ScheduledConfig;
import com.jaezi.common.manager.ThreadManager;
import org.springframework.context.annotation.Configuration;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 定时获取微信用户信息
 */

@Configuration
public class SupplyDiffTask /*extends ScheduledConfig*/ {

//    private final SupplyDiffService supplyDiffService;
//
//    public SupplyDiffTask(SupplyDiffService supplyDiffService) {
//        this.supplyDiffService = supplyDiffService;
//    }
//
//    @Override
//    protected void processTask() {
////        ThreadManager.getInstance().syncExecute(() -> {
////            supplyDiffService.updateSupply();
////        });
//    }
//
//    @Override
//    protected String getCron() {
//        return supplyDiffService.getCron();
//    }
}
