package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.system.dao.ResourceConfigDao;
import com.jaezi.system.model.ResourceConfig;
import com.jaezi.system.vo.ResourceConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation 系统资源配置数据逻辑层
 * @date 2021/07/15 15:37
 * @description
 */
@Service
public class ResourceConfigService extends BaseService<ResourceConfig, ResourceConfigVo> {

    private ResourceConfigDao resourceConfigDao;

    @Autowired
    public void setBaseDao(ResourceConfigDao resourceConfigDao) {
        super.setBaseDao(resourceConfigDao);
        this.resourceConfigDao = resourceConfigDao;
    }
}
