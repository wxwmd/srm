package com.jaezi.srminterface.timed;

import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.config.ScheduledConfig;
import com.jaezi.srminterface.service.WeChatUserService;
import org.springframework.context.annotation.Configuration;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 定时获取微信用户信息
 */

@Configuration
public class WeChatTask /*extends ScheduledConfig*/ {

//    private WeChatUserService weChatUserService;
//
//    public WeChatTask(WeChatUserService weChatUserService) {
//        this.weChatUserService = weChatUserService;
//    }
//
//    @Override
//    protected void processTask() {
//        ThreadManager.getInstance().syncExecute(() -> {
////            weChatUserService.weChatTask();
//        });
//    }
//
//    @Override
//    protected String getCron() {
//        return weChatUserService.getCron();
//    }
}
