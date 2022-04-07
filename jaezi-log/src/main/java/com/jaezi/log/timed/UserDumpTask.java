package com.jaezi.log.timed;

import com.jaezi.common.config.ScheduledConfig;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.log.service.UserDumpService;
import org.springframework.context.annotation.Configuration;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 定时转存日志
 */
@Configuration
public class UserDumpTask extends ScheduledConfig {

    private final UserDumpService userDumpService;

    public UserDumpTask(UserDumpService userDumpService) {
        this.userDumpService = userDumpService;
    }

    @Override
    protected void processTask() {
        ThreadManager.getInstance().syncExecute(() -> {
            userDumpService.userDumpTask();
        });
    }

    @Override
    protected String getCron() {
        return userDumpService.getCron();
    }
}
