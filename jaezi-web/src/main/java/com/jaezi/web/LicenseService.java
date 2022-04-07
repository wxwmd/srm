package com.jaezi.web;

import com.jaezi.common.base.BaseException;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.LicenseCodec;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2019/8/27 15:42
 * @description License验证服务
 */
@Component
public class LicenseService implements InitializingBean {

    @Value("${license.open}")
    private Boolean open;

    @Value("${license.licensePath}")
    private String licensePath;

    @Override
    public void afterPropertiesSet() {
        if (open) {
            try {
                String lic = FileUtil.fileRead(System.getProperty("user.dir") + licensePath);
                //如果系统不存在lic文件则停止服务
                if (ObjectUtils.isEmpty(lic)) {
                    throw new BaseException("license server stop");
                }
                LicenseCodec.decrypt(lic);
            } catch (BaseException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
