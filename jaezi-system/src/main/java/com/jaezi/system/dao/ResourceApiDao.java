package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.ResourceApi;
import com.jaezi.system.vo.ResourceApiVo;
import org.springframework.stereotype.Repository;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 菜单数据访问对象
 */
@Repository
public interface ResourceApiDao extends BaseDao<ResourceApi, ResourceApiVo> {

}
