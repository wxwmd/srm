package com.jaezi.web.ip;

import com.jaezi.common.cache.IpUtils;
import com.jaezi.common.config.ScheduledConfig;
import com.jaezi.common.manager.ThreadManager;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 定时刷新IP黑名单信息
 */

@Configuration
public class IpManageTask extends ScheduledConfig {

    @Override
    protected void processTask() {
        ThreadManager.getInstance().syncExecute(() -> {
            for (Map.Entry<String, Long> ipMap : IpUtils.getIpCache().entrySet()) {
                Long ipTime = ipMap.getValue();
                // 过期了移除缓存
                long currentTime = System.currentTimeMillis();
                if (ipTime > 0 && currentTime - ipTime > IpUtils.getIpExpiration()) {
                    IpUtils.deleteIpMapCache(ipMap.getKey());
                    IpUtils.deleteIpListCache(ipMap.getKey());
                }
            }
        });
    }

    @Override
    protected String getCron() {
        return "*/29 * * * * ?";
    }
}
