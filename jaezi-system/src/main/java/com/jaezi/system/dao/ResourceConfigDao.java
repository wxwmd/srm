package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.ResourceConfig;
import com.jaezi.system.vo.ResourceConfigVo;
import org.springframework.stereotype.Repository;

/**
 * @author yzl
 * @version v1.0
 * @corporation 系统资源配置数据访问层
 * @date 2021/07/15 15:37
 * @description
 */
@Repository
public interface ResourceConfigDao extends BaseDao<ResourceConfig, ResourceConfigVo> {
}
